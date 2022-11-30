# Sound Good Music School, Function-as-a-Service Architecture
Sound Good Music School is entering exponential growth, and the traffic is exceeding the expected online traffic.
SGMS has come to use with a need to migrate their current monolithic architecture to function-as-a-service architecture as they have plans with the newly created function and want to open future options.

## Project Description
This project is developed together as a case study for a thesis made by [Arif Jehda-Oh](https://github.com/arifjehoh) and [Nils Falck](https://github.com/nilfal).

### About Project
This project is on the course IX1351, Data Storage Paradigms, provided by [Leif Lindbäck](https://github.com/leifll) at KTH Royal Institute of Technology.
The project is about designing and implementing an application that communicates to a database service developed by the students.
The project is about implementing features:
- Receive applications from an external user.
- Register applicants as a student.
- Let a student book lessons for their need.
- Match students with an instructor.
- Let student rent instrument.
- Sending out payrolls to instructors.
- Sending out invoices to students.

These features are some of the requirements the project had in the course.

In this project, we aim to do a case study of this project where we provide Sound Good Music School with a migration strategy to be used on the monolithic architecture project to migrate to function-as-a-service architecture.
<img width="2698" alt="KTH exjobb(5)" src="https://user-images.githubusercontent.com/25460850/200197118-1cab9335-4d90-44f2-a89b-8d02f9ec4509.png">


### Project Technologies
This project uses Java as it based programming language.
The functions and the client build with Spring Boot based on Spring Framework.
The client builds on using Spring Boot with Thymeleaf Engine to create webpages and RESTful API requests with Web Services.
The function builds on using Spring Cloud Function and Java Database Connectivity to PostgreSQL Database.
The orchestration and communication between applications based on using [OpenFaaS](https://www.openfaas.com/), [Kubernetes](https://kubernetes.io/), [Kubernetes-in-Docker, KinD](https://kind.sigs.k8s.io/), [Arkade](https://github.com/alexellis/arkade), and [Docker](https://www.docker.com/)

### Roadmap
- [x] Database connection.
- [x] Create simple CRUD functions by using [Strangler Fig Pattern](https://martinfowler.com/bliki/StranglerFigApplication.html)
- [x] Create a client which uses the function and display to view.
- [x] Create the rest of the CRUD functions needed to fulfill the project's features.
- [x] Migrating [Sound Good Music School, monolithic edition](https://github.com/ht100xht22/monolith).

## How to Install
1. Clone the project.
```bash
git clone https://github.com/ht100xht22/fn-sgms.git
```
2. Install Docker
3. Install Arkade
4. Install KinD
5. Create a cluster with KinD.
```bash
kind create cluster
```
6. Install OpenFaaS with Arkade
```bash
arkade install openFaas
```
7. Enter the command and wait for everything to be running.
```bash
kubectl -n openfaas get deployments -l "release=openfaas, app=openfaas" -w
```
8. Enter the command to install `faas-cli`.
```bash
curl -SLsf https://cli.openfaas.com | sudo sh
```
9. Rollout a gateway.
```bash
kubectl rollout status -n openfaas deploy/gateway
```
10. Open up a another terminal window
11. Enter the command.
```bash
kubectl port-forward -n openfaas svc/gateway 8080:8080 &
```
12. Execute the command to generate a password for openfaas ui
```bash
PASSWORD=$(kubectl get secret -n openfaas basic-auth -o jsonpath="{.data.basic-auth-password}" | base64 --decode; echo)
echo -n $PASSWORD | faas-cli login --username admin --password-stdin
```
13. Navigate to [http://localhost:8080/ui/](http://localhost:8080/ui/)
14. Enter your credentials. You can find the password from the terminal you been working on with the command
```bash
echo $PASSWORD
```
Copy the password and enter in the webpage.
15. You are now ready to start working

### How to Run the Project
1. Navigate to [http://localhost:8080/ui/](http://localhost:8080/ui/)
2. Start interactive with the implemented functions.

### How to Use the Project
#### Add Database connectivity
1. (LOCAL) Start up a postgresql database container.
```bash
sh scripts/db-start.sh
```
2. (KUBERNETES) Install postgresql.
```bash
arkade install postgresql
```
3. (KUBERNETES) Export postgresql password from kubernetes.
```bash
export POSTGRES_PASSWORD=$(kubectl get secret --namespace default postgresql -o jsonpath="{.data.postgres-password}" | base64 -d)
```
4. (KUBERNETES) Start postgresql session
```bash
kubectl run postgresql-client --rm --tty -i --restart='Never' --namespace default --image docker.io/bitnami/postgresql:15.0.0-debian-11-r3 --env="PGPASSWORD=$POSTGRES_PASSWORD" --command -- psql --host postgresql -U postgres -d postgres -p 5432
```
5. (KUBERNETES) Open a new terminal and execute port forward.
```bash
kubectl port-forward --namespace default svc/postgresql 5432:5432 & PGPASSWORD="$POSTGRES_PASSWORD" psql --host 127.0.0.1 -U postgres -d postgres -p 5432
```
6. (KUBERNETES) Export hostname with port and database.
```bash
export DB_HOST=$(kubectl get svc postgresql -ojsonpath='{.spec.clusterIP}'):5432/postgres
```
7. Add dependency to function.
```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
	<version>2.7.5</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
<dependency>
	<groupId>org.postgresql</groupId>
	<artifactId>postgresql</artifactId>
	<version>42.5.0</version>
</dependency>
```
8. Add database jdbc connection to function, navigate to the function you want to add connection with the database. Navigate to `application.yaml`. Add this to the end of the code.
```yaml
spring:
  jpa:
    database: postgresql
    show-sql: true
    hibernate:
      ddl-auto: update
  datasource:
    url: "jdbc:postgresql://${DB_HOST:localhost:5432/sgms}"
    username: "postgres"
    password: "${DB_PASSWORD:postgres}"
```
9. Add environment to all FaaS yaml file.
```yaml
environment:
   DB_HOST: ${DB_HOST}
   DB_PASSWORD: ${POSTGRES_PASSWORD}
```
10. (OPTIONAL) To close the databas run the script.
```bash
sh scripts/db-stop.sh
```
#### Working with Function-as-a-Service
1. Download the Spring Cloud Function template for OpenFaas
```bash
faas-cli template pull https://github.com/arifjehoh/openfaas-sb-function-template
```
2. Create a function
```bash
faas-cli new <name of function> --lang=dockerfile -p=<docker-hub-username>
```
3. Start develop on the function, navigate to the src directory and find `Application.java` file and start developing.
4. After implemented function run the YAML file to implement it to OpenFaaS.
```bash
faas-cli up -f <function-name>.yml
```
5. Navigate to [http://localhost:8080/ui/](http://localhost:8080/ui/)
6. Start interactive with the implemented functions.

# Sound Good Music School, Function-as-a-Service Architecture
Sound Good Music School is entering exponential growth, and the traffic is exceeding the expected online traffic.
SGMS has come to use with a need to migrate their current monolithic architecture to function-as-a-service architecture as they have plans with the newly created function and want to open future options.

## Project Description
This project is developed together as a case study for a thesis made by [Arif Jehda-Oh](https://github.com/arifjehoh) and [Nils Falck](https://github.com/nilfal).

### About Project
This project is on the course IX1351, Data Storage Paradigms, provided by [Leif Lindbäck](https://github.com/leifll] at KTH Royal Institute of Technology.
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
- [ ] Database connection.
- [ ] Create simple CRUD functions by using [Strangler Fig Pattern](https://martinfowler.com/bliki/StranglerFigApplication.html)
- [ ] Create a client which uses the function and display to view.
- [ ] Create the rest of the CRUD functions needed to fulfill the project's features.
- [ ] Migrating [Sound Good Music School, monolithic edition](https://github.com/ht100xht22/monolith).

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
arkade install OpenFaaS.
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

## Contributer
https://www.contributor-covenant.org/
https://docs.github.com/en/communities/setting-up-your-project-for-healthy-contributions/setting-guidelines-for-repository-contributors
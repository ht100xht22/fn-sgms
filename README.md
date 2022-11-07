# Sound Good Music School, Function-as-a-Service Architecture
Sound Good Music School is entering exponential growth, and the traffic is exceeding the expected online traffic.
SGMS has come to use with a need to migrate their current monolithic architecture to function-as-a-service architecture as they have plans with the newly created function and want to open future options.

## Project Description
This project is developed together as a case study for a thesis made by [Arif Jehda-Oh](https://github.com/arifjehoh] and [Nils Falck](https://github.com/nilfal].

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

## How to Run the Project

## How to Use the Project

## Contributer
https://www.contributor-covenant.org/
https://docs.github.com/en/communities/setting-up-your-project-for-healthy-contributions/setting-guidelines-for-repository-contributors

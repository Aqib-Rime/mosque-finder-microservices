# Mosque Finder Microservices backend

This project demonstrate the implementation of backend microservices to support Mosque-Finder app.

## Features

* User registration and access with JWT authorization
* Get nearby mosques in a circle
* Notification service
* Refresh token
* api to communicate with mosques
* Rate limiting the request with Redis

## Technologies

* Spring Boot 3.0
* Spring Gateway
* JSON Web Tokens (JWT)
* BCrypt
* Maven
* Kafka
* Redis

## Getting Started

To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+
* Docker

**To build and run the project, follow these steps:**

* Clone the repository: `git clone https://github.com/Aqib-Rime/mosque-finder-microservices.git`
* Navigate to the project directory: cd mosque-finder-microservices
* install and start docker
* run the docker-compose file

```bash
docker-compose up
```

To build and run a particular service, go to the module and run the following commands.

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

-> The application will be available at http://localhost:8080.

## TODOS

1. [x] Add the authentication with JWT
2. [x] Add endpoints to add, remove, find and modify mosque information.
3. [x] Add kafka message broker to communicate with services asynchronously.
4. [ ] Implement Notification service
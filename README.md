# OpenAPI generated server

Spring Boot Server

## Overview
This server was generated by the [OpenAPI Generator](https://openapi-generator.tech) project.
By using the [OpenAPI-Spec](https://openapis.org), you can easily generate a server stub.
This is an example of building a OpenAPI-enabled server in Java using the SpringBoot framework.

```
openapi-generator generate -i softcode-test-assignment-api.yml -g spring --additional-properties=basePackage=com.softcode.employeemanagement,modelPackage=com.softcode.employeemanagement.model,apiPackage=com.softcode.employeemanagement.api,configPackage=com.softcode.employeemanagement.configuration,artifactId=employeemanagement,groupId=com.softcode  -o employee-management/

```


The underlying library integrating OpenAPI to Spring Boot is [springdoc](https://springdoc.org).
Springdoc will generate an OpenAPI v3 specification based on the generated Controller and Model classes.
The specification is available to download using the following url:
http://localhost:8080/v3/api-docs/

Start your server as a simple java application

You can view the api documentation in swagger-ui by pointing to
http://localhost:8080/swagger-ui.html

Change default port value in application.properties

## The Technology Stack

**Java Platform:** Java 8+

**Frameworks:** Spring Framework and its portfolio projects String Boot, Spring Security(Basic), Spring Data JPA (Hibernate)

**Build Tool:** Maven

**IDE:** Intellij IDEA

**Server:** Tomcat as embedded server

**Database:** PostgreSQL 

**REST Client:** Postman, Android Application

**REST API documentation:** Swagger

**Message Broker:** RabbitMQ

## Using Docker for PostgreSQL

For example, to start a postgresql database in a docker container, run:

    docker-compose -f src/main/docker/postgresql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/postgresql.yml down

## Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9000) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
mvn clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=tushar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
mvn initialize sonar:sonar
```

## Using Docker for RabbitMQ

You can start a local RabbitMQ (accessible on http://localhost:15672) with:

    docker-compose -f src/main/docker/rabbitmq.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/rabbitmq.yml down

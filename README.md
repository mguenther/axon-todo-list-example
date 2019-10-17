# Axon Framework - Event-sourced Todo List

[![Build Status](https://travis-ci.org/mguenther/axon-todo-list-example.svg?branch=master)](https://travis-ci.org/mguenther/axon-todo-list-example.svg)

This repository contains an example application that demonstrates how to implement an Event-sourced system using the Axon stack. The Axon stack consists of the [Axon Framework](https://axoniq.io/product-overview/axon-framework), which is used to build the application, and the [Axon Server](https://axoniq.io/product-overview/axon-server), which is a zero-configuration event store that works out-of-the-box.

The application is implemented on top of Spring Boot and uses the Spring Boot starter for Axon. The programming model of Axon integrates quite nicely with what we expect from a Spring experience - hopefully, you'll see this yourself by consulting the code.

Projections are stored using an in-memory H2 database.

The application itself is minimalistic and implements a simple todo list that allows you to create and close todo items.

## Getting Started

First of all, make sure that [Docker](https://www.docker.com/) as well as [Docker Compose](https://docs.docker.com/compose/install/) is installed on your system.

Before you start the example application, you should make sure that you have an instance of Axon Server running. To get you started quickly, this repository contains a `docker-compose.yml` script that launches Axon Server and exposes the necessary ports to the host system.

Go to `src/main/docker` and run

```bash
$> docker-compose run
```

If you haven't done this before, this will pull Axon Server 4.2 from [DockerHub](https://hub.docker.com/r/axoniq/axonserver/). After the image is available on your local system, Axon Server should be up and running in a matter of seconds.

Stopping Axon Server is done via

```bash
$> docker-compose stop
```

and the remaining container (if you won't fire it up again or want to start with a clean slate) can be removed with

```bash
$> docker-compose rm -f
```

The Axon Dashboard is available at [localhost:8024](http://localhost:8024) once Axon Server has been successfully started.

After that, you can start the example application using Maven from the root of the repository.

```bash
$> mvn spring-boot:run
```

Of course, you can also build the application via `mvn clean install` and start the resulting fat JAR.

### Open API UI

The application comes with a OpenAPI integration that is available at [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) once the application has successfully started. The API is documented, so you should be able to figure things out for yourself.

### Axon Dashboard

The application can be inspected using the Axon Dashboard of your locally running Axon Server instance. Just go to [http://localhost:8024](http://localhost:8024), click on the *Overview* selector in the menu bar to the left and you should see a single connection from the application to Axon Server.

### H2 Console

The example application has the H2 console configured. You can take a look at the database at [http://localhost:8080/h2-console](http://localhost:8080/h2-console), connect to the JDBC URL `jdbc:h2:mem:testdb` and see for yourself what the individual projections store.

## License

This work is released under the terms of the Apache 2.0 license.

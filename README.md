# Movies API

## Technologies used:
Technologies used:
* Spring Boot 3.1.2
* Spring Data JPA (Hibernate 6  is the default JPA implementation)
* Maven
* Java 17
* JUnit 5
* [REST Assured](https://rest-assured.io/)

## How to run it
```

$ cd movies-api

$ ./mvnw clean package -Dmaven.test.skip=true

$ ./mvnw spring-boot:run

GET http://localhost:8080/movies/find/interval

```
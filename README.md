# AnimaLand
A simple Spring Boot app exploring animal's tricks.

## Requirements

For building and running the application you need:

- [JDK 17.0.4.1](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3.8.6](https://maven.apache.org)
- [Docker](https://docs.docker.com/)
- Postgres 16.1

## Running the application
1. Build the app:
```shell
   mvn clean install -DskipTests
```
2. To run the database, on the main project folder run : 
```shell
   docker-compose up -d
```
3. To run the app :
```shell
mvn spring-boot:run
```

## About
* The web app is accessible via localhost:8081
* The package structure is by feature.
* Integration with Postgres as a database.
* Dependency Injection via constructor.
* Global Exception Handling using @ControllerAdvise
* Spring Data integration with JPA and Hibernate.
* Testing using Mockito and MockMVC test framework.
* Swagger2 for documentation
* Supporting Pagination on certain endpoints.

## Sample endpoints
#### Retrieve all animals
    GET /zoo/animals/
    Content-Type: application/json 


    {
        "animals": [
            {
                "name": "Oscar",
                "species": "cat",
                "tricks": [
                    "walksOnLaptop"
                ],
                "id": "id1"
            }
        ],
        "pageIndex": 0,
        "pageSize": 20,
        "totalElements": 6,
        "totalPages": 1
    }

``` shell
curl -X GET --header 'Accept: application/json' 'http://localhost:8081/zoo/animals'
```
#### Animal does a trick 
    GET /zoo/animals/{id}/doTrick
    Content-Type: application/json
    
    {
        "trick": "jumps"
    }

``` shell
curl -X GET --header 'Accept: application/json' 'http://localhost:8081/zoo/animals/id1/doTrick'
```

#### Animal learns a new trick
    PUT /zoo/animals/{id}/learnTrick
    Content-Type: application/json

    [
        {
            "trick": "jumps"
        }, 
        {
            "trick": "rollsOver"
        }
    ]

``` shell
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:8081/zoo/animals/id1/learnTrick'
```

## Swagger API docs
http://localhost:8081/zoo/swagger-ui.html

## Future Actions
* Fix docker-compose set up to run both the database and the app
* Enable actuator
* Write more integration and unit tests
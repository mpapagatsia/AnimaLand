FROM maven:3.8.5-openjdk-17

WORKDIR /animaland
COPY target/animaland-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","animaland-0.0.1-SNAPSHOT.jar"]
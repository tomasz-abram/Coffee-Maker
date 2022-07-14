
FROM openjdk:11
COPY ./target/coffeemaker-0.0.1-SNAPSHOT.jar coffeemaker-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","coffeemaker-0.0.1-SNAPSHOT.jar"]
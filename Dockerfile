FROM openjdk:11
LABEL org.opencontainers.image.source = https://github.com/morBrM/Coffee-Maker
COPY ./target/coffeemaker-0.0.1-SNAPSHOT.jar coffeemaker-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","coffeemaker-0.0.1-SNAPSHOT.jar"]
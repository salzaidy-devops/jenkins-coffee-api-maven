# Use Java 17 runtime
FROM eclipse-temurin:17-jre

LABEL authors="alzaidy"

WORKDIR /home/app

COPY target/jenkins-coffee-api-mvn.jar coffee-api-mvn.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "coffee-api-mvn.jar"]
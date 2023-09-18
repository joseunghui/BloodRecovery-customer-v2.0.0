FROM openjdk:8-jdk
ARG JAR_FILE=build/libs/BloodRecovery-customer-v2.0.0-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
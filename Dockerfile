FROM openjdk:11-jdk as builder
ARG JAR_FILE=./FinalProject_4-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","app.war"]


# syntax=docker/dockerfile:experimental
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER Brian Hannaway

COPY pom.xml .
COPY src src

#RUN mvn package
#RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN --mount=type=cache,target=/root/.m2 mvn package
FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /target/docker-boot-intro-0.1.0.jar /app/

ENTRYPOINT ["java", "-jar", "docker-boot-intro-0.1.0.jar"]






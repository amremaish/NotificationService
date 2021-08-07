FROM alpine:3.14
FROM openjdk:8-jdk-alpine
COPY ./init-user-db.sh /init-user-db.sh
RUN /bin/sh init-user-db.sh
VOLUME /tmp
RUN mkdir /code
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
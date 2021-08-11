FROM postgres:11.3-alpine
FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mkdir /code
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
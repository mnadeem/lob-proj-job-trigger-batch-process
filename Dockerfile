FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/lob-proj-job-trigger-batch-process*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
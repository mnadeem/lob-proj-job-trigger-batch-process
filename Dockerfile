FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/lob-proj-job-trigger-batch-process*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+UseSerialGC"
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
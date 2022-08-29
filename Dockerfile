FROM openjdk:16-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN mkdir output/
ENTRYPOINT ["java","-jar","/app.jar"]
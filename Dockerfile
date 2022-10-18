# alpine os w/ jdk 16
FROM openjdk:16-jdk-alpine

# create output/ directory
RUN mkdir output/

# copy & rename .jar file
COPY target/*.jar app.jar

# application entrypoint
ENTRYPOINT ["java","-jar","/app.jar"]
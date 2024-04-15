# maven image for build
FROM maven:3.9.6-eclipse-temurin-22-alpine AS build
# copy pom.xml 
COPY pom.xml /build/
# download dependencies
WORKDIR /build/
RUN mvn dependency:resolve
# copy source/
COPY src/ /build/src/
# build the app with maven
RUN mvn -D maven.test.skip=true package

# alpine os w/ jdk 16
FROM openjdk:23-slim-bullseye
# create output/
RUN mkdir -p /app/output/
# copy & rename .jar file
COPY --from=build /build/target/*.jar /app/run.jar
# Set active profile
ENV spring.profiles.active=docker
# application entrypoint
ENTRYPOINT ["java", "-jar", "/app/run.jar"]

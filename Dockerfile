# maven image for build
FROM maven:3.9.6-sapmachine-21 AS build
# copy pom.xml 
COPY pom.xml /build/
# download dependencies
WORKDIR /build/
RUN mvn dependency:resolve
# copy source/
COPY src/ /build/src/
# build the app with maven
RUN mvn -Dmaven.test.skip=true package

# alpine os w/ jdk 16
FROM openjdk:16-jdk-alpine
# create output/
RUN mkdir -p /app/output/
# copy & rename .jar file
COPY --from=build /build/target/*.jar /app/run.jar
# application entrypoint
ENTRYPOINT ["java","-jar","/app/run.jar"]

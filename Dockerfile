#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

#
#
#
FROM adoptopenjdk/openjdk12-openj9:alpine-slim
RUN mkdir -p /app/_cache
COPY target/mumbanksocialapp-0.0.1-SNAPSHOT.jar /app/app.jar
VOLUME /app/_cache
EXPOSE 8080
CMD ["java", "-Xvirtualized", "-Xshareclasses", "-Xshareclasses:name=sum", "-Xshareclasses:cacheDir=/app/_cache","-Djdk.tls.client.protocols=TLSv1,TLSv1.1,TLSv1.2", "-jar", "/app/app.jar"]
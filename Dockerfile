FROM adoptopenjdk/openjdk12-openj9:alpine-slim
RUN mkdir -p /app/_cache
COPY target/mumbanksocialapp-0.0.1-SNAPSHOT.jar /app/app.jar
VOLUME /app/_cache
EXPOSE 8085
CMD ["java", "-Xvirtualized", "-Xshareclasses", "-Xshareclasses:name=sum", "-Xshareclasses:cacheDir=/app/_cache", "-jar", "/app/app.jar"]
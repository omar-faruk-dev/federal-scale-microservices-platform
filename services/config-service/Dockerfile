FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/config-service-*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app/app.jar"]

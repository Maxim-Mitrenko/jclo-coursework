FROM openjdk:17-oracle

EXPOSE 5500

COPY target/coursework-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
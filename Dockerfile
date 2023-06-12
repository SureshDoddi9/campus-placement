FROM openjdk:11
ADD target/placement-backend-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
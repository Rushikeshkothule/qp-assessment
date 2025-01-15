FROM openjdk:21-jdk
VOLUME /tmp
COPY target/grocery-booking-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
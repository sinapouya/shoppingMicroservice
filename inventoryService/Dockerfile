FROM openjdk:17-jdk-slim

COPY target/*.jar app.jar

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5111","-jar","app.jar"]

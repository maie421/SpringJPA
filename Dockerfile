From adoptopenjdk:8-jdk-hotspot AS builder

ARG JAR_FILE=target/Jungstagram-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} jungstagram.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

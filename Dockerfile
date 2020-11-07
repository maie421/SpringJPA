From adoptopenjdk:8-jdk-hotspot AS builder

VOLUME /tmp

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

From adoptopenjdk:8-jdk-hotspot
ARG JAR_FILE=target/Jungstagram-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/jungstagram.jar"]
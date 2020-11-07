From adoptopenjdk:8-jdk-hotspot AS builder

VOLUME /tmp

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

From adoptopenjdk:8-jdk-hotspot
COPY --from=builder build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
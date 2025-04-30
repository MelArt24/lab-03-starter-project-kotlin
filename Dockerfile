FROM gradle:8.5-jdk17 AS builder
COPY . /app
WORKDIR /app
RUN gradle build --no-daemon

FROM openjdk:17-slim
WORKDIR /app
COPY build/libs/app.jar app.jar
CMD ["java", "-jar", "app.jar"]

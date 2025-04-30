FROM gradle:8.5-jdk17 AS builder
COPY . /app
WORKDIR /app
RUN gradle build --no-daemon

FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/build/libs/Notes.jar app.jar
CMD ["java", "-jar", "app.jar"]

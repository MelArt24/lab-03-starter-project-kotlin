FROM gradle:8.5-jdk17-alpine AS builder
COPY . /app
WORKDIR /app
RUN gradle build --no-daemon

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/app.jar app.jar
CMD ["java", "-jar", "app.jar"]

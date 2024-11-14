FROM maven AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean install -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Whatsapp-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]


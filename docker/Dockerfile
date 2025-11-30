
# Build stage
FROM maven:3.9.9-eclipse-temurin-20 AS builder
WORKDIR /app
COPY . .
RUN mvn -B -DskipTests package

# Run stage
FROM eclipse-temurin:20-jre
WORKDIR /app
COPY --from=builder /app/target/inventory-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

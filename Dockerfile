# 🔧 Step 1: Build with Maven
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

# Only copy files needed for caching dependencies
COPY pom.xml .
COPY src ./src

# Build the app and skip tests
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

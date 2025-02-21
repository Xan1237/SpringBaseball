FROM maven:3.8-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy maven config files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-slim

# Set working directory
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Copy Angular frontend files (assuming they're in src/main/resources/static)
COPY --from=build /app/src/main/resources/static /app/static

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
# Stage 1: Build Angular App
FROM node:18 AS frontend-build
WORKDIR /app
COPY frontend/package.json frontend/package-lock.json ./
RUN npm install
COPY frontend/ .
RUN npm run build --prod

# Stage 2: Build Spring Boot Application
FROM maven:3.9-eclipse-temurin-17 AS backend-build
WORKDIR /app
COPY backend/pom.xml .
RUN mvn dependency:go-offline
COPY backend/ .
RUN mvn clean package -DskipTests

# Stage 3: Create Final Image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy Spring Boot JAR
COPY --from=backend-build /app/target/*.jar app.jar

# Copy Angular build
COPY --from=frontend-build /app/dist/frontend /static

# Expose the port for Spring Boot
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

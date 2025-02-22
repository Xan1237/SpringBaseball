# Stage 1: Build Angular App
FROM node:18 AS Frontend-build
WORKDIR /app
COPY Frontend/package.json Frontend/package-lock.json ./
RUN npm install
COPY Frontend/ .
RUN npm run build --prod

# Stage 2: Build Spring Boot Application
FROM maven:3.9-eclipse-temurin-23 AS Backend-build
WORKDIR /app
COPY Backend/pom.xml .
RUN mvn dependency:go-offline
COPY Backend/ .
RUN mvn clean package -DskipTests

# Stage 3: Create Final Image
FROM eclipse-temurin:23-jdk
WORKDIR /app

# Copy Spring Boot JAR
COPY --from=Backend-build /app/target/*.jar app.jar

# Copy Angular build
COPY --from=Frontend-build /app/dist/Frontend /static

# Expose the port for Spring Boot
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

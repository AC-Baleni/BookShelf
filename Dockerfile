# --- Stage 1: Build the application ---
# Use a Java 24 JDK slim image for building
FROM openjdk:24-ea-jdk-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Add author label as requested
LABEL authors="School EC"

# Copy the Maven wrapper files (if using Maven)
COPY mvnw .
COPY .mvn .mvn

# Make the Maven wrapper script executable
RUN chmod +x mvnw

# Copy the pom.xml file to download dependencies first
# This leverages Docker's layer caching: dependencies are only re-downloaded if pom.xml changes
COPY pom.xml .

# Download project dependencies
# The 'dependency:go-offline' goal downloads all project dependencies
# without building the application, speeding up subsequent builds
RUN ./mvnw dependency:go-offline

# Copy the rest of the application source code
COPY src src

# Build the Spring Boot application into a JAR file
# The 'package' goal compiles the code and creates the executable JAR
RUN ./mvnw package -DskipTests

# --- Stage 2: Create the final lightweight runtime image ---
# Use the same Java 24 JDK slim image for the final application to reduce image size,
# as a specific JRE-slim for EA 24 might not be readily available.
FROM openjdk:24-ea-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the 'builder' stage
# The 'target/*.jar' pattern assumes your JAR is directly in the 'target' directory
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your Spring Boot application listens on (default is 8080)
EXPOSE 8080

# Define the command to run the application when the container starts
# For a Spring Boot JAR, we execute the JAR.
ENTRYPOINT ["java", "-jar", "app.jar"]

# Stage 1: Build
FROM maven:3.9.7-sapmachine-21 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven configuration files first
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .
COPY mvnw.cmd .

# Download all required dependencies into one layer
RUN mvn dependency:go-offline -B

# Copy source files
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


# Stage 2: Runtime
FROM openjdk:21-slim

WORKDIR /app

# Copy the jar file from build stage
COPY --from=build /app/target/*.jar app.jar

# Set environment variables
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

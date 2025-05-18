# Use OpenJDK 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy project files
COPY . .

# Build the Spring Boot app using Maven wrapper
RUN ./mvnw clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application (replace JAR name below)
CMD ["java", "-jar", "target/expense-manager-0.0.1-SNAPSHOT.jar"]

# Use OpenJDK as base image
FROM openjdk:17-jdk-slim
  
  # Set working directory inside container
WORKDIR /app
  
  # Copy JAR file (make sure your jar path matches target/*.jar)
COPY target/*.jar app.jar
  
  # Expose port (same as your Spring Boot app)
EXPOSE 8080
  
  # Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

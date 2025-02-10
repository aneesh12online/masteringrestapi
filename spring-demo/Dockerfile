# Step 1: Use a base image with JDK
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the application JAR file into the container
COPY target/spring-demo-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your application runs on
EXPOSE 8080

# Step 5: Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
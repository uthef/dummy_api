FROM maven:3.8.4-jdk-18

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file to the working directory
COPY pom.xml .

# Run Maven to download dependencies
RUN mvn assembly:assembly

# Expose port 8080
EXPOSE 8080

CMD ["java", "-jar", "target/dummy_api-1.0-jar-with-dependencies.jar"]
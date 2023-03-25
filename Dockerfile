FROM openjdk:18-jdk

# Set the working directory to /app
WORKDIR /app

COPY ./target /app

# Expose port 8080
EXPOSE 8080

CMD ["java", "-jar", "target/dummy_api-1.0-jar-with-dependencies.jar"]

# Use Java 21 image
FROM eclipse-temurin:21-jdk

# Copy jar file
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run application
ENTRYPOINT ["java","-jar","/app.jar"]
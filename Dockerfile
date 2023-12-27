FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy only the pom.xml file and install dependencies to cache dependencies layer
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project and build
COPY . .
RUN mvn clean install

# Copy the JAR file from the target directory
COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8080

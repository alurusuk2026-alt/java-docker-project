# ==========================================
# Stage 1: TEST
# ==========================================
FROM maven:3.9-eclipse-temurin-17 AS test

WORKDIR /app

# Copy dependency file first for better caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Run unit tests
RUN mvn test


# ==========================================
# Stage 2: BUILD
# ==========================================
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven configuration
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the JAR without running tests again
RUN mvn clean package -DskipTests


# ==========================================
# Stage 3: RUNTIME
# ==========================================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Create a non-root user
RUN groupadd --system spring && \
    useradd --system --gid spring spring

# Copy only the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Change ownership
RUN chown spring:spring app.jar

# Run the application as non-root user
USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
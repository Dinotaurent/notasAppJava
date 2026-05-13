# Etapa 1 — build
# Usamos una imagen con Maven y Java 25 para compilar
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# Copiamos primero solo el pom.xml para aprovechar el cache de Docker
# Si el código cambia pero las dependencias no, no re-descarga todo
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline -B

# Ahora copiamos el código y compilamos
COPY src ./src
RUN ./mvnw package -DskipTests -B

# Etapa 2 — runtime
# Imagen más liviana solo con JRE, sin Maven ni JDK
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Usuario no root por seguridad — nunca correr contenedores como root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
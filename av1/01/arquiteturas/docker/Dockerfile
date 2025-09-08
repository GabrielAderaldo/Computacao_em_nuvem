# Estágio de Build: Compila a aplicação com Gradle e JDK 21
FROM gradle:8.8.0-jdk21 AS build
WORKDIR /home/gradle/project

# Copia todo o projeto para o container
COPY . .

# Garante que o gradlew é executável e constrói o projeto
# O --no-daemon é usado para evitar que o daemon do Gradle continue rodando
RUN chmod +x ./gradlew && ./gradlew bootJar --no-daemon

# Estágio de Execução: Roda a aplicação em um ambiente JRE mínimo
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Argumento para o nome do JAR
ARG JAR_FILE=/home/gradle/project/build/libs/arquiteturas-0.0.1-SNAPSHOT.jar

# Copia o JAR construído do estágio de build para a imagem final
COPY --from=build ${JAR_FILE} app.jar

# Cria um usuário e grupo não-root para rodar a aplicação com mais segurança
RUN addgroup --system springgroup && adduser --system --ingroup springgroup springuser
USER springuser

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

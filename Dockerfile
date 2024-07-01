# Utiliza a imagem base do OpenJDK versão 11 com apenas o JRE (Java Runtime Environment) em uma versão reduzida
FROM openjdk:11-jre-slim

# Cria um volume temporário para ser usado pela aplicação
VOLUME /tmp

# Copia o arquivo JAR gerado pela aplicação para dentro do container com o nome 'app.jar'
COPY target/bingo-api-1.0-SNAPSHOT.jar app.jar

# Define o ponto de entrada do container, especificando o comando para iniciar a aplicação
ENTRYPOINT ["java","-jar","/app.jar"]

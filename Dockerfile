FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/spaceship-api-1.0.0.jar spaceship-api.jar
ENTRYPOINT ["java","-jar","/spaceship-api.jar"]
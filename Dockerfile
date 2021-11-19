FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/workmotion.jar

WORKDIR /opt/app

COPY ${JAR_FILE} workmotion-api.jar

ENTRYPOINT ["java","-jar","workmotion-api.jar"]
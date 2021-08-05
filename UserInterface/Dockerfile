FROM openjdk:8

ARG JAR_FILE=./build/libs/UserInterface.jar

WORKDIR /app/mediscreen

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar", "app.jar"]
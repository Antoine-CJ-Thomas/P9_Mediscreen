FROM openjdk:8

ARG JAR_FILE=./build/libs/UserInterface.jar

WORKDIR /app/mediscreen

COPY ${JAR_FILE} UserInterface.jar

ENTRYPOINT ["java","-jar", "UserInterface.jar"]
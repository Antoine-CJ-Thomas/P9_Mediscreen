FROM openjdk:8

ARG JAR_FILE=./build/libs/PatientInformation.jar

WORKDIR /app/mediscreen

COPY ${JAR_FILE} PatientInformation.jar

ENTRYPOINT ["java","-jar", "PatientInformation.jar"]
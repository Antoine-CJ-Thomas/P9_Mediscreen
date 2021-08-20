FROM openjdk:8

ARG JAR_FILE=./build/libs/PatientNote.jar

WORKDIR /app/mediscreen

COPY ${JAR_FILE} PatientNote.jar

ENTRYPOINT ["java","-jar", "PatientNote.jar"]
FROM openjdk:8

ARG JAR_FILE=./build/libs/PatientReport.jar
ARG TXT_FILE=./build/resources/main/triggerTerm.txt

WORKDIR /app/mediscreen

COPY ${JAR_FILE} PatientReport.jar
COPY ${TXT_FILE} triggerTerm.txt

ENTRYPOINT ["java","-jar", "PatientReport.jar"]
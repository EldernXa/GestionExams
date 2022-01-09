FROM maven:3.6.3 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package

FROM openjdk:16

ARG JAR_FILE=exams-0.0.1-SNAPSHOT.war
WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","exams-0.0.1-SNAPSHOT.war"]
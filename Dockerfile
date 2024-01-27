FROM openjdk:8-jdk-alpine
LABEL email='dslim@strato.co.kr'

RUN echo "Asia/Seoul" > /etc/timezone

ARG PROFILE=
ARG FILENAME=wrp-core.jar

ENV SERVER_ENV=${PROFILE}

COPY ./${FILENAME} wrp-core.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${SERVER_ENV}", "-jar","/wrp-core.jar"]
###########
# PREPARE #
###########
FROM alpine/xml as init

COPY . .
RUN cat pom.xml | xq --raw-output .project.artifactId > artifactId.init.output \
 && cat pom.xml | xq --raw-output .project.version > version.init.output \
 && ARTIFACT_ID=$(cat artifactId.init.output) \
 && VERSION=$(cat version.init.output) \
 && APPNAME=${ARTIFACT_ID}-${VERSION}.jar \
 && echo $'#!/bin/sh \njava '\$JAVA_OPTS' --enable-preview -jar '$APPNAME'' > ./entrypoint.sh \
 && chmod +x ./entrypoint.sh

###########
# BUILDER #
###########
FROM maven:3.8-eclipse-temurin  as builder

COPY --from=init version.init.output .
COPY --from=init artifactId.init.output .
COPY . .
#RUN --mount=type=cache,id=m2-cache,sharing=shared,target=/root/.m2  
#RUN mvn clean install -s settings.xml

RUN --mount=type=cache,target=/root/.m2 mvn clean install -f pom.xml -s settings.xml

##########
# REVIEW #
##########
FROM sonarsource/sonar-scanner-cli:latest as review

ARG SONAR_ANALISY_TOKEN=""

ENV SONAR_ANALISY_TOKEN $SONAR_ANALISY_TOKEN

COPY --from=builder . .
RUN VERSION=$(cat version.init.output) \
 && PROJECT=$(cat artifactId.init.output) \
 && sonar-scanner \
        -Dproject.settings=./devops/sonar-project.properties \
        -Dsonar.projectVersion=${VERSION} \
        -Dsonar.login=${SONAR_ANALISY_TOKEN} 

###########
# RUNTIME #
###########
FROM eclipse-temurin:17.0.4_8-jre as runtime

COPY --from=review /usr/src/pom.xml .
COPY --from=builder /target/*.jar .
COPY --from=builder /pom.xml .
COPY --from=init /entrypoint.sh .
#ENV JAVA_OPTS="$JAVA_OPTS_MEMORY  -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:+ShowCodeDetailsInExceptionMessages $JAVA_OPTS_CONFIG -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["./entrypoint.sh"]




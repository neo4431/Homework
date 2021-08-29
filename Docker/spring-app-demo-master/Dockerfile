FROM openjdk:8-alpine

WORKDIR /single-stage

COPY /target/websocket-demo-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","websocket-demo-0.0.1-SNAPSHOT.jar"]
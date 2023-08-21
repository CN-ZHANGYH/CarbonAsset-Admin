FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD carbon-admin.jar /carbon-admin.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/carbon-admin.jar"]

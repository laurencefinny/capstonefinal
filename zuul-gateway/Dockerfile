FROM openjdk:8-jdk-alpine
COPY target/zuul-gateway-0.0.1-SNAPSHOT.jar zuul.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/zuul.jar"]
FROM openjdk:8-jdk-alpine
COPY target/eureka-discovery-server-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/eureka.jar"]
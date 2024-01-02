FROM openjdk:17
ARG JAR_FILE=target/*.jar
ADD target/transportation-0.0.1-SNAPSHOT.jar .jar
COPY ${JAR_FILE} transportation-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","transportation-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mkdir -p /opt/erasmus/back
ENV JAR_FILE=target/*-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/erasmus/backapp.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/erasmus/backapp.jar"]


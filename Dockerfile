FROM openjdk:8u171
WORKDIR /opt/javaSrv
CMD java -jar app.jar
EXPOSE 8080
COPY build/libs/sampleapi-0.0.1.jar app.jar
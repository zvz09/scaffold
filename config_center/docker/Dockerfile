FROM openjdk:8-stretch
RUN mkdir /app
COPY config_center-0.0.1-SNAPSHOT.jar  /app/

CMD java -jar /app/config_center-0.0.1-SNAPSHOT.jar  --spring.profiles.active=dev

EXPOSE 7006
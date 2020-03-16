FROM openjdk:13.0
ADD target/SecurityDocker-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar SecurityDocker-0.0.1-SNAPSHOT.jar
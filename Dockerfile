FROM openjdk:17
ADD target/rest_api_rt-0.0.1-SNAPSHOT.jar docker-rest_api_rt.jar
EXPOSE 8080
ENTRYPOINT ["java" , "-jar", "/docker-rest_api_rt.jar"]
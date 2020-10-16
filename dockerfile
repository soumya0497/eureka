FROM openjdk:alpine
ADD /target/EurekaServiceManagement-0.0.1.jar eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]
EXPOSE 8077


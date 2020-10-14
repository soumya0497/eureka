FROM openjdk:alpine
ADD /EurekaServiceManagement-0.0.1.jar eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]
EXPOSE 8077


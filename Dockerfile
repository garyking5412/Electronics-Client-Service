FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Electronics-SpringBoot-Client-Service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3001
ENTRYPOINT ["java","-jar","app.jar"]
CMD ["java", "-Djava.cgroup.disable=true", "-jar", "app.jar"]
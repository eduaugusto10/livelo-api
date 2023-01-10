FROM openjdk:11-jre
EXPOSE 5000
WORKDIR /app
COPY target/application.jar .
ENTRYPOINT [ "java", "-jar", "application.jar" ]
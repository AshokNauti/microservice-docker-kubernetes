# Java project docker file is two stage process #
# STAGE 1: Build a JAR file #
FROM maven:3.6.3-jdk-8-slim AS user-service-maven-build
WORKDIR /home/ash/userapp
COPY . /home/ash/userapp
RUN mvn -f /home/ash/userapp/pom.xml clean package -DskipTests

# STAGE 2: Create an Image
FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8000
COPY --from=user-service-maven-build /home/ash/userapp/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
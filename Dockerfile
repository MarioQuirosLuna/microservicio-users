FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD ./target/microservicio-users-0.0.1-SNAPSHOT.jar microservicio-users.jar

ENTRYPOINT [ "java", "-jar", "microservicio-users.jar" ]
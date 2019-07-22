FROM openjdk:latest
COPY ./ ./

RUN ./gradlew clean build

WORKDIR ./build/libs

ENTRYPOINT [ "java", "-jar", "game-of-life-1.0-SNAPSHOT.jar" ]

FROM gradle:jdk11 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build -x test

FROM openjdk:11-jre-slim
ENV ARTIFACT=catalogue-1.0.0.jar
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/$ARTIFACT /app/$ARTIFACT
WORKDIR /app
CMD ["java","-jar","catalogue-1.0.0.jar"]
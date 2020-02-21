FROM openjdk:13
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /usr/src/app/pro-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","pro-api.jar"]

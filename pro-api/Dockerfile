FROM maven:3.6.3-jdk-13
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
COPY . /usr/src/app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/pro-api-1.0.jar"]

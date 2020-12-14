FROM openjdk:11-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/medical-request.jar
WORKDIR /app
ENTRYPOINT java -jar medical-request.jar
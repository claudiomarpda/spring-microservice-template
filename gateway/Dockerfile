FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY build/libs/*.jar app.jar
EXPOSE 8890
ENTRYPOINT ["java","-jar","/app.jar"]
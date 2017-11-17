FROM java:8
ENV EUREKA_SERVER_PORT 8761
ADD eureka-server-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
CMD [ "-Djava.security.egd=file:/dev/./urandom" ]
EXPOSE $EUREKA_SERVER_PORT
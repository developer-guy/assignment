FROM java:8
ENV MONGO_USER bapaydin67
ENV MONGO_PASSWORD 3239966bb
ENV MONGO_PORT 13046
ENV MONGO_HOST ds113046.mlab.com
ENV MONGO_DB assignment
ENV EUREKA_HOST http://172.17.0.2:8761/eureka/
ADD todo-app-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
CMD [ "-Djava.security.egd=file:/dev/./urandom" , "--MONGO_USER=$MONGO_USER" , "--MONGO_PASSWORD=$MONGO_PASSWORD" , "--MONGO_PORT=$MONGO_PORT" , "--MONGO_HOST=$MONGO_HOST" , "--MONGO_DB=$MONGO_DB" , "--EUREKA_HOST=$EUREKA_HOST" ]
EXPOSE 8080
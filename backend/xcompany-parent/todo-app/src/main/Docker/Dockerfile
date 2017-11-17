FROM java:8
ADD todo-app-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom" , "-Denvironment=$MONGO_USER" , "-Denvironment=$MONGO_PASSWORD" , "-Denvironment=$MONGO_DB_PORT" , "-Denvironment=$MONGO_HOST" , "-Denvironment=$MONGO_DB" , "-Denvironment=$EUREKA_HOST" , "-jar", "/app.jar" ]
EXPOSE 8080

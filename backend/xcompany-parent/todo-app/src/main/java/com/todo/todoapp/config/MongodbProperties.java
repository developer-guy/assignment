package com.todo.todoapp.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongodbProperties {
    private String username;
    private String password;
    private String host;
    private String database;
    private int port;
}

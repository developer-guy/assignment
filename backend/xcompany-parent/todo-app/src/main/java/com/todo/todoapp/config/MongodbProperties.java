package com.todo.todoapp.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongodbProperties {
    private String username;
    private String password;
    private String host;
    private int port;
    private String database;
}

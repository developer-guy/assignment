package com.todo.todoapp.config;

import com.mongodb.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = {"com.todo.todoapp.repository"})
@RequiredArgsConstructor
@Slf4j
public class MongoConfiguration extends AbstractMongoConfiguration {
    private final MongodbProperties mongodbProperties;

    @Bean
    public Mongo mongo() throws UnknownHostException {
        return mongoClient();
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory(), null);
    }

    @Override
    protected String getDatabaseName() {
        return mongodbProperties.getDatabase();
    }

    @Bean
    public MongoClient mongoClient() {
        List<MongoCredential> credentialsList = new ArrayList<>();
        credentialsList.add(MongoCredential.createCredential(mongodbProperties.getUsername(), getDatabaseName(), mongodbProperties.getPassword().toCharArray()));
        ServerAddress primary = new ServerAddress(mongodbProperties.getHost(), mongodbProperties.getPort());
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(4).socketKeepAlive(true).build();
        return new MongoClient(Arrays.asList(primary), credentialsList, mongoClientOptions);
    }

}
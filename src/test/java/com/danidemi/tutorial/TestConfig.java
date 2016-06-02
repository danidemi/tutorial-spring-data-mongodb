package com.danidemi.tutorial;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
@PropertySource("classpath:mongo.properties")
public class TestConfig {

    @Value("${mongo.host}")
    String mongoHost;

    @Value("${mongo.port}")
    int mongoPort;

    @Value("${mongo.db}")
    String mongoDb;

    @Bean
    public static PropertySourcesPlaceholderConfigurer ppc() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }

    @Bean public MongoDbFactory mongoDbFactory() throws Exception {
        MongoClient client = new MongoClient( new ServerAddress(mongoHost, mongoPort) );
        return new SimpleMongoDbFactory(client, mongoDb);
    }

    @Bean public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

}


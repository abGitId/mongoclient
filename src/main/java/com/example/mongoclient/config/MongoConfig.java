package com.example.mongoclient.config;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.mongoclient.repository",
        mongoTemplateRef = "mongoTemplate")
public class MongoConfig {
//    SimpleMongoClientDbFactory
//            SimpleMongoClientDatabaseFactory

    @Value("${spring.data.mongodb.local.uri}")
    private String uri;


    @Bean
    public MongoTemplate mongoTemplate(){

        //MongoClient client = MongoClients.create(uri);

        SimpleMongoClientDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(uri);
        MongoTemplate mongoTemplate = new MongoTemplate(factory);
        return mongoTemplate;
    }
}

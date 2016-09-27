package com.gkatzioura.spring.security.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by gkatzioura on 9/27/16.
 */
@Configuration
@Profile("customuserdetails")
public class MongoConfiguration {

    @Bean
    public MongoClient createConnection() {

        //put your real ip here
        return new MongoClient("172.17.0.2:27017");
    }
}

package com.gkatzioura.sqstesting.config;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gkatziourasemmanouil on 25/02/16.
 */
@Configuration
public class SQSConfig {

    @Value("${queue.endpoint}")
    private String endpoint;

    @Value("${queue.name}")
    private String queueName;

    @Bean
    public AmazonSQSClient createSQSClient() {

        AmazonSQSClient amazonSQSClient = new AmazonSQSClient(new BasicAWSCredentials("",""));
        amazonSQSClient.setEndpoint(endpoint);

        amazonSQSClient.createQueue(queueName);

        return amazonSQSClient;
    }

}

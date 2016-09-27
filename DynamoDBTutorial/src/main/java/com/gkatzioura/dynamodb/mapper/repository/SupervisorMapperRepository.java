package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.gkatzioura.dynamodb.mapper.entities.Supervisor;

/**
 * Created by gkatzioura on 9/27/16.
 */
public class SupervisorMapperRepository {

    private DynamoDBMapper dynamoDBMapper;

    public SupervisorMapperRepository(AmazonDynamoDB amazonDynamoDB) {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public void insertSupervisor(Supervisor supervisor) {
        dynamoDBMapper.save(supervisor);
    }
}

package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.gkatzioura.dynamodb.mapper.entities.Supervisor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        public Supervisor getSupervisor(String company,String factory) {

            Map<String,String> expressionAttributesNames = new HashMap<>();
            expressionAttributesNames.put("#company","company");
            expressionAttributesNames.put("#factory","factory");

            Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
            expressionAttributeValues.put(":company",new AttributeValue().withS(company));
            expressionAttributeValues.put(":factory",new AttributeValue().withS(factory));

            DynamoDBQueryExpression<Supervisor> dynamoDBQueryExpression = new DynamoDBQueryExpression<Supervisor>()
                    .withIndexName("FactoryIndex")
                    .withKeyConditionExpression("#company = :company and #factory = :factory ")
                    .withExpressionAttributeNames(expressionAttributesNames)
                    .withExpressionAttributeValues(expressionAttributeValues)
            .withConsistentRead(false);

            List<Supervisor> supervisor = dynamoDBMapper.query(Supervisor.class,dynamoDBQueryExpression);

            if(supervisor.size()>0) {
                return supervisor.get(0);
            } else {
                return null;
            }
        }
}

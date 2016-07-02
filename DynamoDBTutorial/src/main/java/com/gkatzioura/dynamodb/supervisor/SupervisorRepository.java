package com.gkatzioura.dynamodb.supervisor;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.*;

/**
 * Created by gkatzioura on 7/2/16.
 */
public class SupervisorRepository {

    private AmazonDynamoDB amazonDynamoDB;

    public static final String TABLE_NAME = "Supervisors";

    public SupervisorRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void insertSupervisor(String name,String company,String factory) {

        Map<String,AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put("name",new AttributeValue().withS(name));
        attributeValues.put("company",new AttributeValue().withS(company));
        attributeValues.put("factory",new AttributeValue().withS(factory));

        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName("Supervisors")
                .withItem(attributeValues);

        PutItemResult putItemResult = amazonDynamoDB.putItem(putItemRequest);
    }

    public Map<String ,AttributeValue> getSupervisor(String company,String factory) {

        List<Map<String,AttributeValue>> items = new ArrayList<>();

        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#company","company");
        expressionAttributesNames.put("#factory","factory");

        Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":company",new AttributeValue().withS(company));
        expressionAttributeValues.put(":factory",new AttributeValue().withS(factory));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TABLE_NAME)
                .withKeyConditionExpression("#company = :company and #factory = :factory ")
                .withIndexName("FactoryIndex")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues);

        QueryResult queryResult = amazonDynamoDB.query(queryRequest);

        List<Map<String,AttributeValue>> attributeValues = queryResult.getItems();

        if(attributeValues.size()>0) {
            return attributeValues.get(0);
        } else {
            return null;
        }
    }

}
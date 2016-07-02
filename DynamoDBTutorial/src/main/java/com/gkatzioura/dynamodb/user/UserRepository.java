package com.gkatzioura.dynamodb.user;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gkatzioura on 7/1/16.
 */
public class UserRepository {

    private AmazonDynamoDB amazonDynamoDB;

    public static final String TABLE_NAME = "Users";

    public UserRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void insertUser(String email, String fullName, Date registerDate) {

        Map<String,AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put("email",new AttributeValue().withS(email));
        attributeValues.put("fullname",new AttributeValue().withS(fullName));
        attributeValues.put("registerDate",new AttributeValue().withN(Long.toString(registerDate.getTime())));

        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName(TABLE_NAME)
                .withItem(attributeValues);

        PutItemResult putItemResult = amazonDynamoDB.putItem(putItemRequest);
    }

    public Map<String,AttributeValue> getUser(String email) {

        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#email","email");

        Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":emailValue",new AttributeValue().withS(email));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TABLE_NAME)
                .withKeyConditionExpression("#email = :emailValue")
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

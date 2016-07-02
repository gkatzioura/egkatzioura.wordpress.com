package com.gkatzioura.dynamodb.login;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.*;

/**
 * Created by gkatzioura on 7/2/16.
 */
public class LoginRepository {

    private AmazonDynamoDB amazonDynamoDB;

    public static final String TABLE_NAME = "Logins";

    public LoginRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void insertLogin(String user, Date date) {

        Map<String,AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put("email",new AttributeValue().withS(user));
        attributeValues.put("timestamp",new AttributeValue().withN(Long.toString(date.getTime())));

        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName(TABLE_NAME)
                .withItem(attributeValues);

        PutItemResult putItemResult = amazonDynamoDB.putItem(putItemRequest);
    }

    public List<Map<String ,AttributeValue>> queryLoginsBetween(String email, Date from, Date to) {

        List<Map<String,AttributeValue>> items = new ArrayList<>();

        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#email","email");
        expressionAttributesNames.put("#timestamp","timestamp");

        Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":emailValue",new AttributeValue().withS(email));
        expressionAttributeValues.put(":from",new AttributeValue().withN(Long.toString(from.getTime())));
        expressionAttributeValues.put(":to",new AttributeValue().withN(Long.toString(to.getTime())));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TABLE_NAME)
                .withKeyConditionExpression("#email = :emailValue and #timestamp BETWEEN :from AND :to ")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues);

        Map<String,AttributeValue> lastKey = null;

        do {

            QueryResult queryResult = amazonDynamoDB.query(queryRequest);
            List<Map<String,AttributeValue>> results = queryResult.getItems();
            items.addAll(results);
            lastKey = queryResult.getLastEvaluatedKey();
        } while (lastKey!=null);

        return items;
    }

}

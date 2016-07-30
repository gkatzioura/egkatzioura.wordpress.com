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

    public Integer countLogins(String email) {
        List<Map<String,AttributeValue>> items = new ArrayList<>();

        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#email","email");

        Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":emailValue",new AttributeValue().withS(email));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TABLE_NAME)
                .withKeyConditionExpression("#email = :emailValue")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues)
                .withSelect(Select.COUNT);

        Map<String,AttributeValue> lastKey = null;
        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String,AttributeValue>> results = queryResult.getItems();
        return queryResult.getCount();
    }

    public List<Map<String,AttributeValue>> fetchLoginsDesc(String email) {

        List<Map<String,AttributeValue>> items = new ArrayList<>();

        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#email","email");

        Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":emailValue",new AttributeValue().withS(email));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TABLE_NAME)
                .withKeyConditionExpression("#email = :emailValue")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues)
                .withScanIndexForward(false);

        Map<String,AttributeValue> lastKey = null;

        do {

            QueryResult queryResult = amazonDynamoDB.query(queryRequest);
            List<Map<String,AttributeValue>> results = queryResult.getItems();
            items.addAll(results);
            lastKey = queryResult.getLastEvaluatedKey();
            queryRequest.withExclusiveStartKey(lastKey);
        } while (lastKey!=null);

        return items;
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
            queryRequest.setExclusiveStartKey(lastKey);
        } while (lastKey!=null);

        return items;
    }

    public List<String> scanLogins(Date date) {

        List<String> emails = new ArrayList<>();


        Map<String, String> attributeNames = new HashMap<String, String >();
        attributeNames.put("#timestamp", "timestamp");


        Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
        attributeValues.put(":from", new AttributeValue().withN(Long.toString(date.getTime())));


        ScanRequest scanRequest = new ScanRequest()
                .withTableName(TABLE_NAME)
                .withFilterExpression("#timestamp < :from")
                .withExpressionAttributeNames(attributeNames)
                .withExpressionAttributeValues(attributeValues)
                .withProjectionExpression("email");

        Map<String,AttributeValue> lastKey = null;

        do {

            ScanResult scanResult = amazonDynamoDB.scan(scanRequest);

            List<Map<String,AttributeValue>> results = scanResult.getItems();
            results.forEach(r->emails.add(r.get("email").getS()));
            lastKey = scanResult.getLastEvaluatedKey();
            scanRequest.setExclusiveStartKey(lastKey);
        } while (lastKey!=null);

        return emails;
    }

}

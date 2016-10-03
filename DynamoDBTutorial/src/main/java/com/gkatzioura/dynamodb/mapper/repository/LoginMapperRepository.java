package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.gkatzioura.dynamodb.login.LoginRepository;
import com.gkatzioura.dynamodb.mapper.entities.Login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gkatzioura on 10/2/16.
 */
public class LoginMapperRepository {

    private DynamoDBMapper dynamoDBMapper;

    public LoginMapperRepository(AmazonDynamoDB amazonDynamoDB) {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public void insertLogin(Login login) {
        dynamoDBMapper.save(login);
    }

    public Login getLogin(String email, Long date) {

        Login login = dynamoDBMapper.load(Login.class, email, date);
        return login;
    }

    public List<Login> queryLoginsBetween(String email, Long from, Long to) {

        Map<String, String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#email", "email");
        expressionAttributesNames.put("#timestamp", "timestamp");

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":emailValue", new AttributeValue().withS(email));
        expressionAttributeValues.put(":from", new AttributeValue().withN(Long.toString(from)));
        expressionAttributeValues.put(":to", new AttributeValue().withN(Long.toString(to)));

        DynamoDBQueryExpression<Login> queryExpression = new DynamoDBQueryExpression<Login>()
                .withKeyConditionExpression("#email = :emailValue and #timestamp BETWEEN :from AND :to ")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues);

        return dynamoDBMapper.query(Login.class, queryExpression);
    }

    public List<Login> scanLogins(Long date) {

        Map<String, String> attributeNames = new HashMap<String, String>();
        attributeNames.put("#timestamp", "timestamp");

        Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
        attributeValues.put(":from", new AttributeValue().withN(date.toString()));

        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression()
                .withFilterExpression("#timestamp < :from")
                .withExpressionAttributeNames(attributeNames)
                .withExpressionAttributeValues(attributeValues);

        List<Login> logins = dynamoDBMapper.scan(Login.class, dynamoDBScanExpression);

        return logins;
    }

    public List<Login> scanLogins(Long date,Integer workers) {

        Map<String, String> attributeNames = new HashMap<String, String>();
        attributeNames.put("#timestamp", "timestamp");

        Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
        attributeValues.put(":from", new AttributeValue().withN(date.toString()));

        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression()
                .withFilterExpression("#timestamp < :from")
                .withExpressionAttributeNames(attributeNames)
                .withExpressionAttributeValues(attributeValues);

        List<Login> logins = dynamoDBMapper.parallelScan(Login.class, dynamoDBScanExpression,workers);

        return logins;
    }
}
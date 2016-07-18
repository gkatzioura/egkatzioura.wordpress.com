package com.gkatzioura.dynamodb.organization;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemResult;
import com.amazonaws.services.dynamodbv2.model.KeysAndAttributes;
import com.gkatzioura.dynamodb.login.LoginRepository;
import com.gkatzioura.dynamodb.supervisor.SupervisorRepository;
import com.gkatzioura.dynamodb.user.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gkatzioura on 7/14/16.
 */
public class OrganizationRepository {

    private AmazonDynamoDB amazonDynamoDB;

    public OrganizationRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public Map<String,List<Map<String,AttributeValue>>> getMultipleInformation(String email,String name) {

        Map<String,KeysAndAttributes> requestItems = new HashMap<>();

        List<Map<String,AttributeValue>> userKeys = new ArrayList<>();
        Map<String,AttributeValue> userAttributes = new HashMap<>();
        userAttributes.put("email",new AttributeValue().withS(email));
        userKeys.add(userAttributes);
        requestItems.put(UserRepository.TABLE_NAME,new KeysAndAttributes().withKeys(userKeys));

        List<Map<String,AttributeValue>> supervisorKeys = new ArrayList<>();
        Map<String,AttributeValue> supervisorAttributes = new HashMap<>();
        supervisorAttributes.put("name",new AttributeValue().withS(name));
        supervisorKeys.add(supervisorAttributes);
        requestItems.put(SupervisorRepository.TABLE_NAME,new KeysAndAttributes().withKeys(supervisorKeys));

        BatchGetItemRequest batchGetItemRequest = new BatchGetItemRequest();
        batchGetItemRequest.setRequestItems(requestItems);

        BatchGetItemResult batchGetItemResult = amazonDynamoDB.batchGetItem(batchGetItemRequest);

        Map<String,List<Map<String,AttributeValue>>> responses = batchGetItemResult.getResponses();

        return responses;
    }
}

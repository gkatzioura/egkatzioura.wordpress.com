package com.gkatzioura.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Created by gkatzioura on 6/26/16.
 */
public class ItemInserter {

    private AmazonDynamoDB amazonDynamoDB;

    public ItemInserter(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void insertUsers() {

        Map<String,AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put("email",new AttributeValue().withS("jon@doe.com"));
        attributeValues.put("fullname",new AttributeValue().withS("Jon Doe"));

        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName("Users")
                .withItem(attributeValues);

        PutItemResult putItemResult = amazonDynamoDB.putItem(putItemRequest);
    }

    public void insetBatchLogins() {

        Map<String,AttributeValue> firstAttributeValues = new HashMap<>();
        firstAttributeValues.put("email",new AttributeValue().withS("jon@doe.com"));

        Long date = new Date().getTime();

        firstAttributeValues.put("timestamp",new AttributeValue().withN(Long.toString(date)));

        PutRequest firstPutRequest = new PutRequest();
        firstPutRequest.setItem(firstAttributeValues);

        WriteRequest firstWriteRequest = new WriteRequest();
        firstWriteRequest.setPutRequest(firstPutRequest);


        Map<String,AttributeValue> secondAttributeValues = new HashMap<>();
        secondAttributeValues.put("email",new AttributeValue().withS("jon@doe.com"));
        secondAttributeValues.put("timestamp",new AttributeValue().withN(Long.toString(date+100)));

        PutRequest secondPutRequest = new PutRequest();
        secondPutRequest.setItem(secondAttributeValues);

        WriteRequest secondWriteRequest = new WriteRequest();
        secondWriteRequest.setPutRequest(secondPutRequest);

        List<WriteRequest> batchList = new ArrayList<WriteRequest>();
        batchList.add(firstWriteRequest);
        batchList.add(secondWriteRequest);

        Map<String, List<WriteRequest>> batchTableRequests = new HashMap<String, List<WriteRequest>>();
        batchTableRequests.put("Logins",batchList);

        BatchWriteItemRequest batchWriteItemRequest = new BatchWriteItemRequest();
        batchWriteItemRequest.setRequestItems(batchTableRequests);

        amazonDynamoDB.batchWriteItem(batchWriteItemRequest);
    }

    public void insertSupervisor() {

        Map<String,AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put("name",new AttributeValue().withS("Random SuperVisor"));
        attributeValues.put("company",new AttributeValue().withS("Random Company"));
        attributeValues.put("factory",new AttributeValue().withS("Jon Doe"));


        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName("Supervisors")
                .withItem(attributeValues);

        PutItemResult putItemResult = amazonDynamoDB.putItem(putItemRequest);
    }

}

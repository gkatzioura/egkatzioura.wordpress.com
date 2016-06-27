package com.gkatzioura.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.junit.Test;

/**
 * Created by gkatzioura on 6/26/16.
 */
public class ItemInserterTest {

    @Test
    public void insertToTables() {

        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");

        ItemInserter itemInserter = new ItemInserter(amazonDynamoDB);
        itemInserter.insertUsers();
    }

    @Test
    public void batchInsert() {

        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");

        ItemInserter itemInserter = new ItemInserter(amazonDynamoDB);
        itemInserter.insetBatchLogins();
    }

    @Test
    public void insertSupervisor() {

        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");

        ItemInserter itemInserter = new ItemInserter(amazonDynamoDB);
        itemInserter.insertSupervisor();
    }
}

package com.gkatzioura.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gkatzioura on 6/26/16.
 */
public class ItemInserterTest {

    private AmazonDynamoDB amazonDynamoDB;
    private ItemInserter itemInserter;

    @Before
    public void setUp() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");
        this.amazonDynamoDB = amazonDynamoDB;
        this.itemInserter = new ItemInserter(amazonDynamoDB);
    }

    @Test
    public void insertToTables() {

        itemInserter.insertUsers();
    }

    @Test
    public void batchInsert() {

        itemInserter.insetBatchLogins();
    }

    @Test
    public void insertSupervisor() {

        itemInserter.insertSupervisor();
    }
}

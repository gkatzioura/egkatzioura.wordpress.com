package com.gkatzioura.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.junit.Test;

/**
 * Created by gkatzioura on 6/23/16.
 */
public class TableCreatorTest {

    @Test
    public void createTables() {

        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");

        TableCreator tableCreator = new TableCreator(amazonDynamoDB);
        tableCreator.createLoginsTable();
        tableCreator.createSupervisor();
        tableCreator.createUserTable();
        tableCreator.createCompany();
    }

}

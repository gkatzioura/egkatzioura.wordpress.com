package com.gkatzioura.dynamodb.supervisor;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.gkatzioura.dynamodb.TableCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by gkatzioura on 7/2/16.
 */
public class SupervisorRepositoryTest {

    private AmazonDynamoDB amazonDynamoDB;
    private SupervisorRepository supervisorRepository;

    @Before
    public void setUp() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");
        this.amazonDynamoDB = amazonDynamoDB;
        this.supervisorRepository = new SupervisorRepository(amazonDynamoDB);
        TableCreator tableCreator = new TableCreator(amazonDynamoDB);
        tableCreator.deleteSupervisorsTable();
        tableCreator.createSupervisor();
    }

    @Test
    public void testGetSupervisor() {

        supervisorRepository.insertSupervisor("John Doe","Sun","Athens");

        Map<String,AttributeValue> item = supervisorRepository.getSupervisor("Sun","Athens");

        Assert.assertEquals("John Doe",item.get("name").getS());
    }

}
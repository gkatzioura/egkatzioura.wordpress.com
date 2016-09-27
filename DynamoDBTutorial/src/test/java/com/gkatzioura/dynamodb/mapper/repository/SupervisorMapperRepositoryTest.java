package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.gkatzioura.dynamodb.TableCreator;
import com.gkatzioura.dynamodb.mapper.entities.Supervisor;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gkatzioura on 9/27/16.
 */
public class SupervisorMapperRepositoryTest {

    private AmazonDynamoDB amazonDynamoDB;
    private SupervisorMapperRepository supervisorMapperRepository;

    private static final String emailPrefix = "john";

    @Before
    public void setUp() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");
        this.amazonDynamoDB = amazonDynamoDB;
        this.supervisorMapperRepository = new SupervisorMapperRepository(amazonDynamoDB);
        TableCreator tableCreator = new TableCreator(amazonDynamoDB);
        tableCreator.deleteSupervisorsTable();
        tableCreator.createSupervisor();
    }

    @Test
    public void insertSupervisor() {

        Supervisor supervisor = new Supervisor();
        supervisor.setName("John Doe");
        supervisor.setCompany("Company Name");
        supervisor.setFactory("London Factory");

        supervisorMapperRepository.insertSupervisor(supervisor);
    }

}

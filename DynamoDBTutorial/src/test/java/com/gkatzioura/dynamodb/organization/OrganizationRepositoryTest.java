package com.gkatzioura.dynamodb.organization;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.gkatzioura.dynamodb.TableCreator;
import com.gkatzioura.dynamodb.login.LoginRepository;
import com.gkatzioura.dynamodb.supervisor.SupervisorRepository;
import com.gkatzioura.dynamodb.user.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by gkatzioura on 7/14/16.
 */
public class OrganizationRepositoryTest {

    private AmazonDynamoDB amazonDynamoDB;
    private OrganizationRepository organizationRepository;
    private LoginRepository loginRepository;
    private UserRepository userRepository;
    private SupervisorRepository supervisorRepository;

    @Before
    public void setUp() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");
        this.amazonDynamoDB = amazonDynamoDB;
        this.organizationRepository = new OrganizationRepository(amazonDynamoDB);
        this.userRepository = new UserRepository(amazonDynamoDB);
        this.supervisorRepository = new SupervisorRepository(amazonDynamoDB);
        TableCreator tableCreator = new TableCreator(amazonDynamoDB);
        tableCreator.deleteUsersTable();
        tableCreator.deleteLoginsTable();
        tableCreator.deleteSupervisorsTable();
        tableCreator.createUserTable();
        tableCreator.createLoginsTable();
        tableCreator.createSupervisor();
    }

    @Test
    public void test() {

        userRepository.insertUser("john@doe.com","John Doe",new Date());
        supervisorRepository.insertSupervisor("John Doe","Sun","Athens");

        Map<String,List<Map<String,AttributeValue>>> vals = organizationRepository.getMultipleInformation("john@doe.com","John Doe");

        Assert.assertTrue(vals.get(UserRepository.TABLE_NAME).size()>0);
        Assert.assertTrue(vals.get(SupervisorRepository.TABLE_NAME).size()>0);
    }

}

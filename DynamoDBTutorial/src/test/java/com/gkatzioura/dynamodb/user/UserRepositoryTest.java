package com.gkatzioura.dynamodb.user;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.gkatzioura.dynamodb.TableCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * Created by gkatzioura on 7/2/16.
 */
public class UserRepositoryTest {

    private AmazonDynamoDB amazonDynamoDB;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");
        this.amazonDynamoDB = amazonDynamoDB;
        this.userRepository = new UserRepository(amazonDynamoDB);
        TableCreator tableCreator = new TableCreator(amazonDynamoDB);
        tableCreator.deleteUsersTable();
        tableCreator.createUserTable();
    }

    @Test
    public void testGetUser() {

        userRepository.insertUser("me@test.com","Mr mean",new Date());
        Map<String,AttributeValue> user = userRepository.getUser("me@test.com");

        Assert.assertEquals(user.get("fullname").getS(),"Mr mean");
    }

    @Test
    public void testGetRegisterDate() {

        userRepository.insertUser("me@test.com","Mr mean",new Date());
        Map<String,AttributeValue> user = userRepository.getRegisterDate("me@test.com");

        System.out.println("d");
    }

    @Test
    public void updateUser() {

        userRepository.insertUser("me@test.com","Mr mean",new Date());

        Map<String,AttributeValue> user = userRepository.getUser("me@test.com");

        userRepository.updateName("me@test.com","John Doe");

        Map<String,AttributeValue> updatedUser = userRepository.getUser("me@test.com");

        Assert.assertNotEquals(user.get("fullname").getS(),updatedUser.get("fullname").getS());
    }

    @Test
    public void updateUserConditionally() throws ConditionalCheckFailedException {

        userRepository.insertUser("me@test.com","Mr mean",new Date());

        Map<String,AttributeValue> user = userRepository.getUser("me@test.com");

        userRepository.updateConditionallyWithExpression("me@test.com","John Doe","Mr");

        Map<String,AttributeValue> updatedUser = userRepository.getUser("me@test.com");

        Assert.assertNotEquals(user.get("fullname").getS(),updatedUser.get("fullname").getS());
    }

    @Test
    public void testUpdateConditionallyWithAttributeEntries() throws ConditionalCheckFailedException {

        userRepository.insertUser("me@test.com","Mr mean",new Date());

        Map<String,AttributeValue> user = userRepository.getUser("me@test.com");

        userRepository.updateConditionallyWithAttributeEntries("me@test.com","John Doe","Mar");

        Map<String,AttributeValue> updatedUser = userRepository.getUser("me@test.com");

        Assert.assertNotEquals(user.get("fullname").getS(),updatedUser.get("fullname").getS());
    }

    @Test
    public void createAtomicCounter() {

        userRepository.insertUser("me@test.com","Mr mean",new Date());

        Map<String,AttributeValue> user = userRepository.getUser("me@test.com");

        userRepository.addUpdateCounter("me@test.com");

        Map<String,AttributeValue> userWithCounter = userRepository.getUser("me@test.com");

        userRepository.updateAndIncreaseCounter("me@test.com","John doe");

        Map<String,AttributeValue> counterIncreased = userRepository.getUser("me@test.com");

        Assert.assertEquals(userWithCounter.get("counter").getN(),"0");
        Assert.assertEquals(counterIncreased.get("counter").getN(),"1");

    }

}

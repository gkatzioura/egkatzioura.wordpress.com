package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.gkatzioura.dynamodb.TableCreator;
import com.gkatzioura.dynamodb.mapper.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gkatzioura on 9/26/16.
 */
public class UserMapperRepositoryTest {

    private AmazonDynamoDB amazonDynamoDB;
    private UserMapperRepository userMapperRepository;

    private static final String emailPrefix = "john";

    @Before
    public void setUp() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");
        this.amazonDynamoDB = amazonDynamoDB;
        this.userMapperRepository = new UserMapperRepository(amazonDynamoDB);
        TableCreator tableCreator = new TableCreator(amazonDynamoDB);
        tableCreator.deleteUsersTable();
        tableCreator.createUserTable();
    }

    @Test
    public void testInsertUser() {

        User user = new User();
        user.setRegisterDate(new Date().getTime());
        user.setFullName("John Doe");
        user.setEmail("john@doe.com");

        userMapperRepository.insert(user);
    }

    @Test
    public void testBatchUserInsert() {

        List<User> users = new ArrayList<>();

        for(int i=0;i<10;i++) {

            String email = emailPrefix+i+"@doe.com";
            User user = new User();
            user.setRegisterDate(new Date().getTime());
            user.setFullName("John Doe");
            user.setEmail("john@doe.com");
            users.add(user);
        }

        userMapperRepository.insert(users);
    }

    @Test
    public void testBatchDelete() {

        testBatchUserInsert();
        List<User> users = new ArrayList<>();

        for(int i=0;i<10;i++) {

            String email = emailPrefix+i+"@doe.com";
            User user = new User();
            user.setRegisterDate(new Date().getTime());
            user.setFullName("John Doe");
            user.setEmail("john@doe.com");
            users.add(user);
        }

        userMapperRepository.delete(users);
    }

    @Test
    public void testGetUser() {

        final String email = "me@test.com";

        User user = new User();
        user.setEmail(email);
        user.setFullName("Mr mean");
        user.setRegisterDate(new Date().getTime());
        userMapperRepository.insert(user);

        User retrievedUser = userMapperRepository.getUser(email);

        Assert.assertEquals(user.getFullName(),retrievedUser.getFullName());
    }


}
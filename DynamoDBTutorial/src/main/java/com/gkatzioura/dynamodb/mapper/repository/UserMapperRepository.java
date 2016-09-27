package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.gkatzioura.dynamodb.mapper.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gkatzioura on 9/22/16.
 */
public class UserMapperRepository {

    private DynamoDBMapper dynamoDBMapper;

    public UserMapperRepository(AmazonDynamoDB amazonDynamoDB) {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public void insert(User user) {

        dynamoDBMapper.save(user);
    }

    public void insert(List<User> users) {

        dynamoDBMapper.batchWrite(users,new ArrayList<>());
    }

    public void delete(List<User> users) {
        dynamoDBMapper.batchDelete(users);
    }

}

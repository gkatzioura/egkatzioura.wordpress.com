package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.gkatzioura.dynamodb.mapper.entities.Company;

/**
 * Created by gkatzioura on 9/26/16.
 */
public class CompanyMapperRepository {

    private DynamoDBMapper dynamoDBMapper;

    public CompanyMapperRepository(AmazonDynamoDB amazonDynamoDB) {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public void insertRepo(Company company) {

        dynamoDBMapper.save(company);
    }

}

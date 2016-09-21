package com.gkatzioura.dynamodb.mapper.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Created by gkatzioura on 9/21/16.
 */
@DynamoDBTable(tableName="Supervisors")
public class Supervisor {

    private String name;
    private String company;
    private String factory;

    @DynamoDBHashKey(attributeName="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "FactoryIndex",attributeName = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "FactoryIndex",attributeName = "factory")
    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}

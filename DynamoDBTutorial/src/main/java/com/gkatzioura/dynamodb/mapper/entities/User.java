package com.gkatzioura.dynamodb.mapper.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;

/**
 * Created by gkatzioura on 9/20/16.
 */
@DynamoDBTable(tableName="Users")
public class User {

    private String email;
    private String fullName;
    private Long registerDate;

    @DynamoDBHashKey(attributeName="email")
    public String getEmail() {
        return email;
    }

    @DynamoDBAttribute(attributeName="fullname")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @DynamoDBAttribute(attributeName="registerDate")
    public Long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Long registerDate) {
        this.registerDate = registerDate;
    }
}

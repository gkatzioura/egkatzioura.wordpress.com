package com.gkatzioura.dynamodb.mapper.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

/**
 * Created by gkatzioura on 9/21/16.
 */
@DynamoDBTable(tableName="Companies")
public class Company {

    private String name;
    private String subsidiary;
    private String ceo;

    @DynamoDBHashKey(attributeName="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBRangeKey(attributeName = "subsidiary")
    public String getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(String subsidiary) {
        this.subsidiary = subsidiary;
    }

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "CeoIndex",attributeName = "ceo")
    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }
}

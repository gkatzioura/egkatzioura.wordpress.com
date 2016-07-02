package com.gkatzioura.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

/**
 * Created by gkatzioura on 6/27/16.
 */
public class ItemUpdater {

    private AmazonDynamoDB amazonDynamoDB;

    public ItemUpdater(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }



}

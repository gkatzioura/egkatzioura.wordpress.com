package com.gkatzioura.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gkatzioura on 6/23/16.
 */
public class TableCreator {

    private AmazonDynamoDB amazonDynamoDB;

    public TableCreator(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void createUserTable() {

        List<KeySchemaElement> elements = new ArrayList<KeySchemaElement>();
        KeySchemaElement keySchemaElement = new KeySchemaElement()
                .withKeyType(KeyType.HASH)
                .withAttributeName("email");
        elements.add(keySchemaElement);

        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();

        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("email")
                .withAttributeType(ScalarAttributeType.S));

        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName("Users")
                .withKeySchema(elements)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L))
                .withAttributeDefinitions(attributeDefinitions);

        amazonDynamoDB.createTable(createTableRequest);
    }

    public void createLoginTable() {

        List<KeySchemaElement> elements = new ArrayList<KeySchemaElement>();
        KeySchemaElement hashKey = new KeySchemaElement()
                .withKeyType(KeyType.HASH)
                .withAttributeName("email");
        KeySchemaElement rangeKey = new KeySchemaElement()
                .withKeyType(KeyType.RANGE)
                .withAttributeName("timestamp");
        elements.add(hashKey);
        elements.add(rangeKey);


        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();

        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("email")
                .withAttributeType(ScalarAttributeType.S));


        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("timestamp")
                .withAttributeType(ScalarAttributeType.N));

        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName("Logins")
                .withKeySchema(elements)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L))
                .withAttributeDefinitions(attributeDefinitions);


        amazonDynamoDB.createTable(createTableRequest);
    }

    public void createSupervisor() {

        List<KeySchemaElement> elements = new ArrayList<>();
        KeySchemaElement hashKey = new KeySchemaElement()
                .withKeyType(KeyType.HASH)
                .withAttributeName("name");
        elements.add(hashKey);

        List<GlobalSecondaryIndex> globalSecondaryIndices = new ArrayList<>();

        ArrayList<KeySchemaElement> indexKeySchema = new ArrayList<>();

        indexKeySchema.add(new KeySchemaElement()
                .withAttributeName("company")
                .withKeyType(KeyType.HASH));  //Partition key
        indexKeySchema.add(new KeySchemaElement()
                .withAttributeName("factory")
                .withKeyType(KeyType.RANGE));  //Sort key


        GlobalSecondaryIndex factoryIndex = new GlobalSecondaryIndex()
                .withIndexName("FactoryIndex")
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits((long) 10)
                        .withWriteCapacityUnits((long) 1))
                .withKeySchema(indexKeySchema)
                .withProjection(new Projection().withProjectionType(ProjectionType.ALL));
        globalSecondaryIndices.add(factoryIndex);

        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();

        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("name")
                .withAttributeType(ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("company")
                .withAttributeType(ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("factory")
                .withAttributeType(ScalarAttributeType.S));

        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName("Supervisors")
                .withKeySchema(elements)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L))
                .withGlobalSecondaryIndexes(factoryIndex)
                .withAttributeDefinitions(attributeDefinitions);

        amazonDynamoDB.createTable(createTableRequest);
    }

    public void createCompany() {

        List<KeySchemaElement> elements = new ArrayList<>();
        KeySchemaElement hashKey = new KeySchemaElement()
                .withKeyType(KeyType.HASH)
                .withAttributeName("name");
        KeySchemaElement rangeKey = new KeySchemaElement()
                .withKeyType(KeyType.RANGE)
                .withAttributeName("subsidiary");

        elements.add(hashKey);
        elements.add(rangeKey);

        List<LocalSecondaryIndex> localSecondaryIndices = new ArrayList<>();

        ArrayList<KeySchemaElement> indexKeySchema = new ArrayList<>();

        indexKeySchema.add(new KeySchemaElement()
                .withAttributeName("name")
                .withKeyType(KeyType.HASH));
        indexKeySchema.add(new KeySchemaElement()
                .withAttributeName("ceo")
                .withKeyType(KeyType.RANGE));

        LocalSecondaryIndex ceoIndex = new LocalSecondaryIndex()
                .withIndexName("CeoIndex")
                .withKeySchema(indexKeySchema)
                .withProjection(new Projection().withProjectionType(ProjectionType.ALL));
        localSecondaryIndices.add(ceoIndex);

        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();

        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("name")
                .withAttributeType(ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("subsidiary")
                .withAttributeType(ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("ceo")
                .withAttributeType(ScalarAttributeType.S));

        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName("Companies")
                .withKeySchema(elements)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L))
                .withLocalSecondaryIndexes(localSecondaryIndices)
                .withAttributeDefinitions(attributeDefinitions);

        amazonDynamoDB.createTable(createTableRequest);
    }

}

package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.gkatzioura.dynamodb.TableCreator;
import com.gkatzioura.dynamodb.mapper.entities.Company;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gkatzioura on 9/26/16.
 */
public class CompanyMapperRepositoryTest {

    private AmazonDynamoDB amazonDynamoDB;
    private CompanyMapperRepository companyMapperRepository;

    @Before
    public void setUp() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");
        this.amazonDynamoDB = amazonDynamoDB;
        this.companyMapperRepository = new CompanyMapperRepository(amazonDynamoDB);
        TableCreator tableCreator = new TableCreator(amazonDynamoDB);
        tableCreator.deleteCompaniesTable();
        tableCreator.createCompany();
    }

    @Test
    public void testAddCompany() {

        Company company = new Company();
        company.setName("General");
        company.setSubsidiary("Subsidiary_1");
        company.setCeo("John Doe");

        companyMapperRepository.insertRepo(company);
    }

}
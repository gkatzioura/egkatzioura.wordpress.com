package com.gkatzioura.dynamodb.mapper.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.gkatzioura.dynamodb.TableCreator;
import com.gkatzioura.dynamodb.login.LoginRepository;
import com.gkatzioura.dynamodb.mapper.entities.Login;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

/**
 * Created by gkatzioura on 10/2/16.
 */
public class LoginMapperRepositoryTest {

    private AmazonDynamoDB amazonDynamoDB;
    private LoginMapperRepository loginRepository;

    private static final String EMAIL = "me@test.com";

    @Before
    public void setUp() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setEndpoint("http://localhost:8000");
        this.amazonDynamoDB = amazonDynamoDB;
        this.loginRepository = new LoginMapperRepository(amazonDynamoDB);
        TableCreator tableCreator = new TableCreator(amazonDynamoDB);
        tableCreator.deleteLoginsTable();
        tableCreator.createLoginsTable();
    }

    @Test
    public void testGetUser() {

        final Date date = new Date();

        Login login = new Login();
        login.setEmail(EMAIL);
        login.setTimestamp(date.getTime());
        loginRepository.insertLogin(login);

        Login retrievedLogin = loginRepository.getLogin(EMAIL,date.getTime());

        Assert.assertNotNull(retrievedLogin);
    }

    @Test
    public void testQueryLoginsBetween() {

        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        ZonedDateTime initial = zonedDateTime.minus(30, ChronoUnit.DAYS);

        for (int i=0;i<30;i++) {

            ZonedDateTime tempData = initial.plus(i,ChronoUnit.DAYS);

            Login login = new Login();
            login.setEmail(EMAIL);
            login.setTimestamp(tempData.toInstant().getEpochSecond());
            loginRepository.insertLogin(login);
        }

        List<Login> logins = loginRepository.queryLoginsBetween(EMAIL,initial.toEpochSecond(),initial.plus(14,ChronoUnit.DAYS).toEpochSecond());

        Assert.assertEquals(logins.size(),15);
    }

    @Test
    public void testScanLogins() {

        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        ZonedDateTime initial = zonedDateTime.minus(30, ChronoUnit.DAYS);

        for (int i=0;i<30;i++) {

            ZonedDateTime tempData = initial.plus(i,ChronoUnit.DAYS);

            Login login = new Login();
            login.setEmail(EMAIL);
            login.setTimestamp(tempData.toInstant().getEpochSecond());
            loginRepository.insertLogin(login);
        }

        List<Login> logins = loginRepository.scanLogins(initial.plus(15,ChronoUnit.DAYS).toEpochSecond());

        Assert.assertEquals(logins.size(),15);
    }

    @Test
    public void testParallelScanLogins() {

        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        ZonedDateTime initial = zonedDateTime.minus(30, ChronoUnit.DAYS);

        for (int i=0;i<30;i++) {

            ZonedDateTime tempData = initial.plus(i,ChronoUnit.DAYS);

            Login login = new Login();
            login.setEmail(EMAIL);
            login.setTimestamp(tempData.toInstant().getEpochSecond());
            loginRepository.insertLogin(login);
        }

        List<Login> logins = loginRepository.scanLogins(initial.plus(15,ChronoUnit.DAYS).toEpochSecond(),4);

        Assert.assertEquals(logins.size(),15);
    }


}
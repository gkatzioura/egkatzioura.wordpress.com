package com.gkatzioura.springdatareadreplica.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.AbstractDataSource;

public class LoadBalancedDatasource extends AbstractDataSource {

    private RoundRobinPolicy roundRobinPolicy;

    public LoadBalancedDatasource(DataSource[] dataSources) {
        roundRobinPolicy = new RoundRobinPolicy();
        roundRobinPolicy.init(Arrays.asList(dataSources));
    }

    @Override
    public Connection getConnection() throws SQLException {
        DataSource dataSource = roundRobinPolicy.next();
        Connection connection = dataSource.getConnection();
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        DataSource dataSource = roundRobinPolicy.next();
        return dataSource.getConnection(username, password);
    }

}

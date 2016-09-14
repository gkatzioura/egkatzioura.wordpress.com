package com.gkatzioura.spring.security.config;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.nio.file.Files;

/**
 * Created by gkatzioura on 9/2/16.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource createDataSource() {

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:"+System.getProperty("java.io.tmpdir")+"/database");

        return dataSource;
    }

}

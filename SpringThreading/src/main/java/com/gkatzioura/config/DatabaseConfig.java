package com.gkatzioura.config;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by gkatzioura on 4/26/17.
 */
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource createMainDataSource() {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:"+System.getProperty("java.io.tmpdir")+"/testdata");
        return ds;
    }
}

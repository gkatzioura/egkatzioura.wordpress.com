package com.gkatzioura.springdata.jpa.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by gkatzioura on 6/2/16.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource createDataSource() throws Exception {

        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setJdbcUrl("jdbc:postgresql://172.17.0.2:5432/postgres?&user=postgres&password=postgres");
        ds.setDriverClass("org.postgresql.Driver");

        return ds;
    }

}

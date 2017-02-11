package com.gkatzioura.hibernate.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by gkatzioura on 2/6/17.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource createMainDataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://172.17.0.2:5432/postgres");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("postgres");

        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springPostgresDataSource");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

}

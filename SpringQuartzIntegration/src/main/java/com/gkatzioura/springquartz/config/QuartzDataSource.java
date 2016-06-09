package com.gkatzioura.springquartz.config;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by gkatzioura on 6/6/16.
 */
@Configuration
public class QuartzDataSource {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    @Bean
    public DataSource dataSource() {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:"+TMP_DIR+"/test");

        return ds;
    }

}

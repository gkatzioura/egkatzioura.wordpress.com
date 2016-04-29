package com.gkatzioura.bootdatabaseinitialization.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by gkatzioura on 29/4/2016.
 */
@Repository
public class UserRepository {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_USERNAMES_QUERY = "SELECT name FROM Users";

    @Autowired
    public UserRepository(@Qualifier("mainDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<String> getNames() {

        List<String> userNames = jdbcTemplate.queryForList(SELECT_ALL_USERNAMES_QUERY,String.class);
        return userNames;
    }
}

package com.gkatzioura.springdatareadreplica.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.gkatzioura.springdatareadreplica.datasource.LoadBalancedDatasource;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.gkatzioura",
        includeFilters= @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "readEntityManagerFactory"
)
public class LoadBalancedReadOnlyEntityManagerConfiguration {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.readUrls}")
    private String[] readUrls;

    @Bean
    public DataSource readDataSource() throws Exception {
        DataSource[] dataSources = new DataSource[readUrls.length];

        for(int i=0;i< readUrls.length;i++) {
            DataSource dataSource = DataSourceBuilder.create()
                                .url(readUrls[i])
                                .username(username)
                                .password(password)
                                .driverClassName("org.postgresql.Driver")
                                .build();
            dataSources[i] = dataSource;
        }

        LoadBalancedDatasource loadBalancedDatasource = new LoadBalancedDatasource(dataSources);
        return loadBalancedDatasource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("readDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                      .packages("com.gkatzioura.springdatareadreplica")
                      .persistenceUnit("read")
                      .build();
    }

}

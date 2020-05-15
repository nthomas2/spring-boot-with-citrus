package com.nthomas.springbootwithcitrus.config;

import com.consol.citrus.config.CitrusSpringConfig;
import com.consol.citrus.db.driver.JdbcDriver;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.jdbc.server.JdbcServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@TestConfiguration
@Import(CitrusSpringConfig.class)
public class CitrusConfig {
    @Bean
    public JdbcServer jdbcServer() {
        return CitrusEndpoints.jdbc()
                .server()
                .host("localhost")
                .databaseName("testdb")
                .port(4567)
                .timeout(10000L)
                .autoStart(true)
                .autoTransactionHandling(false)
                .build();
    }

    @Bean
    @DependsOn("jdbcServer")
    public SimpleDriverDataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(JdbcDriver.class);
        dataSource.setUrl("jdbc:citrus:http://localhost:4567/testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}

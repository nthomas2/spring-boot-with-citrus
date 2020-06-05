package com.nthomas.springbootwithcitrus.config;

import com.consol.citrus.config.CitrusSpringConfig;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.dsl.runner.DefaultTestRunner;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

@TestConfiguration
@Import({TestConfig.class, CitrusSpringConfig.class})
public class CitrusConfig {

    @Bean
    public TestRunner testRunner(ApplicationContext applicationContext, TestContext testContext) {
        return new DefaultTestRunner(applicationContext, testContext);
    }

    @Bean
    @Lazy // @LocalServerPort isn't instantiated until app actually starts up
    public HttpClient httpClient(@LocalServerPort int localServerPort) {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://localhost:" + localServerPort)
                .build();
    }
}

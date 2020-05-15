package com.nthomas.springbootwithcitrus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.equalTo;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(not(equalTo("/error"))).build()
                .genericModelSubstitutes(ResponseEntity.class)
                .enableUrlTemplating(true)
                .tags(new Tag("Rest", "Rest endpoints"))
                ;
    }
}

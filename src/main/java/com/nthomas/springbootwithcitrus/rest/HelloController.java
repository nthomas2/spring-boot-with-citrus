package com.nthomas.springbootwithcitrus.rest;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Hello", tags = "Rest")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}

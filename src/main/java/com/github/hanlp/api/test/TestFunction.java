package com.github.hanlp.api.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class TestFunction {
    @Bean
    public Function<String, String> uppercase() {
        return value -> value.toUpperCase();
    }
}

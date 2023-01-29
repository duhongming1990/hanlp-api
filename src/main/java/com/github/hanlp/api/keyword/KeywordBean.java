package com.github.hanlp.api.keyword;

import com.hankcs.hanlp.HanLP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;

@Configuration
public class KeywordBean {
    @Bean
    public Function<String, List<String>> extractKeyword() {
        return document -> HanLP.extractKeyword(document, 5);
    }
}

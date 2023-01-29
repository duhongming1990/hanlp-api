package com.github.hanlp.api.summary;

import com.hankcs.hanlp.HanLP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class SummaryBean {
    @Bean
    public Function<String, String> extractSummary() {
//        return document -> HanLP.extractSummary(document, 3);
        return document -> HanLP.getSummary(document, 100);
    }
}

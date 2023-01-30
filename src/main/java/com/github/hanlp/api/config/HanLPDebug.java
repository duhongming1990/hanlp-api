package com.github.hanlp.api.config;

import com.hankcs.hanlp.HanLP;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "hanlp", name = "debug", havingValue = "true")
public class HanLPDebug {
    @Bean
    public void debug() {
        HanLP.Config.enableDebug();
    }
}

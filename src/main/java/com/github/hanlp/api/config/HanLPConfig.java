package com.github.hanlp.api.config;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HanLPConfig {
    @Bean
    public Segment segment() {
        Segment segment = HanLP.newSegment();
        segment.enableAllNamedEntityRecognize(true);
        segment.enableNumberQuantifierRecognize(true);
        return segment;
    }
}

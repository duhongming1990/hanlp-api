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

        //词性标注参见：http://www.hankcs.com/nlp/part-of-speech-tagging.html
        segment.enablePartOfSpeechTagging(true);

        segment.enableAllNamedEntityRecognize(true);
        segment.enableNumberQuantifierRecognize(true);
        return segment;
    }
}

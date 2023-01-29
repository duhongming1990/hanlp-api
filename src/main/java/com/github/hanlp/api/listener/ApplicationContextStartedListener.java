package com.github.hanlp.api.listener;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationContextStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        CustomDictionary.add("跳表");
        CustomDictionary.add("二分查找");
    }
}

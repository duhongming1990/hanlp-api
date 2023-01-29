package com.github.hanlp.api;

import com.github.hanlp.api.listener.ApplicationContextStartedListener;
import com.hankcs.hanlp.HanLP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HanLPApplication {
    public static void main(String[] args) {
        HanLP.Config.enableDebug();
        SpringApplication app = new SpringApplication(HanLPApplication.class);
        app.addListeners(new ApplicationContextStartedListener());
        app.run(args);

    }
}

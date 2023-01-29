package com.github.hanlp.api.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author duhongming
 * @Email duhongming@lixiang.com
 * @Date 2022/2/7 17:33
 * @Description
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class Swagger3Config {
    @Value("${swagger.enable.bsp-diag-service:true}")
    private Boolean enableSwagger;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.github.hanlp.api.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }

    private Docket getDocketInstance(String basePackage, String groupName) {
        return new Docket(DocumentationType.OAS_30)
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .groupName(groupName)
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("hanlp-api")
                .description("HanLP API")
                .version("0.1.0-SNAPSHOT")
                .build();
    }
}

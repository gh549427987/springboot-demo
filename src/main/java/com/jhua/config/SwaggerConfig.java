package com.jhua.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2);
    }

    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo() {

        Contact contact = new Contact("Jhua", "https://www.cnblogs.com/jhua", "549427987@qq.com");
        return new ApiInfo(
                "Jhua的API文档",
                "遇事不决，可问清风",
                "v1.0",
                "https://www.cnblogs.com/jhua",
                contact,
                "Apache 2.0",
                "https://www.cnblogs.com/jhua",
                new ArrayList<>()
        );
    }

}

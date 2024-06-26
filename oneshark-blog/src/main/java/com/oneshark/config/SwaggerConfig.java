package com.oneshark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration// 表明是一个配置类
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oneshark.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("一只鲨鱼", "http://www.oneshark.cn", "oneshark5@163.com");
        return new ApiInfoBuilder()
                .title("文档标题---前端页面接口已完成！")
                .description("文档描述---后台管理系统待完成！")
                .contact(contact)   // 联系方式
                .version("1.1.0")  // 版本
                .build();
    }
}
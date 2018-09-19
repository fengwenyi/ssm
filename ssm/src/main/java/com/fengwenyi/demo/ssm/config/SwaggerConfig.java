package com.fengwenyi.demo.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fengwenyi.demo.ssm.controller.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful API Demo")
                .description("基于SSM、Swagger2构建RESTful风格的API，并自动生成文档")
                .termsOfServiceUrl("https://fengwenyi.com")
                .contact(new Contact("Wenyi Feng", "https://fengwenyi.com", "xfsy2014@gmail.com"))
                .version("1.0")
                .license("license")
                .licenseUrl("https://fengwenyi.com/license")
                .build();
    }

}

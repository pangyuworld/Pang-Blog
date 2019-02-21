package com.pang.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pang
 * @version V1.0
 * @ClassName: SwaggerConfig
 * @Package com.pang.blog.config
 * @description: swagger2配置
 * @date 2019/1/23 8:47
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pang.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Pang-Blog")
                .termsOfServiceUrl("http://127.0.0.1:8877/")
                .version("1.0")
                .contact("小胖儿")
                .termsOfServiceUrl("http://localhost:8888")
                .description("Pang-Blog接口合集")
                .build();
    }
}

package com.ruoyi.web.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author zyh
 * @date 2023/7/8 23:33
 * @desc IntelliJ IDEA
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("swagger-bootstrap-ui-demo CarbonAsset")
                        .description("# CarbonAsset RESTful APIs")
                        .termsOfServiceUrl("http://localhost:8080/")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2.3.5版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.ruoyi.web.controller.carbon"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}

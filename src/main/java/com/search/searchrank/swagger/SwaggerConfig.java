package com.search.searchrank.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * Swagger2的配置类
 * apiInfo() 增加API相关信息
 * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
 * 本例采用指定扫描的包路径来定义指定要建立API的目录。
 * @Description
 * @Date 2018年10月31日 10:06
 * @Param []
 * @return springfox.documentation.spring.web.plugins.Docket
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 全局参数
     *
     * @return
     */
    private List<Parameter> parameter() {
        List<Parameter> params = new ArrayList<>();
        params.add(new ParameterBuilder().name("Authorization")
                .description("Authorization Bearer token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build());
        return params;
    }

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * @Author xiongyou
     * @Description
     * @Date 2018年10月31日 10:06
     * @Param []
     * @return springfox.documentation.spring.web.plugins.Docket
     **/
    @Bean
    public Docket sysApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hik.edereteam"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parameter());
    }


    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @Description
     * @Date 2018年10月31日 10:07
     * @Param []
     * @return springfox.documentation.service.ApiInfo
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(" statistics-service api ")
                .description("statistics-service 微服务")
                .termsOfServiceUrl("")
                .contact("edere")
                .version("1.0")
                .build();
    }

}

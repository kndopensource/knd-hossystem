package com.hos.admin.config.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * swagger配置类
 *
 * @author 众神
 * @date 2017年6月1日19:42:59
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Bean(value = "defaultApi")
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))                         //这里采用包含注解的方式来确定要显示的接口
//                //.apis(RequestHandlerSelectors.basePackage("com.hos.admin.modular.system.controller"))     //这里采用包扫描的方式来确定要显示的接口
//                .paths(PathSelectors.any())
//                // 配置swagger接口安全校验规则
//                .securitySchemes(securitySchemes())
//                // 配置swagger接口安全校验上下文中的信息（包含安全权限与安全校验生效的接口路径）
//                .securityContexts(securityContexts())
//                .build();
//    }
    @Bean(value = "hosCollectApi")
    public Docket hosCollectApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("HosCollectApi")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 包扫描范围（对指定的包下进行扫描，如果标注有相关swagger注解，则生成相应文档）
//                .apis(RequestHandlerSelectors.basePackage(SCAN_BASE_PACKAGE))
                // 过滤掉哪些path不用生成swagger
                .paths(PathSelectors.any())
                .build()
                // 忽略该参数在swagger上的显示
                .ignoredParameterTypes()
                // 配置swagger接口安全校验规则
                .securitySchemes(securitySchemes())
                // 配置swagger接口安全校验上下文中的信息（包含安全权限与安全校验生效的接口路径）
                .securityContexts(securityContexts())
                .apiInfo(apiInfo())
                // swagger生效
                .enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hos Doc")
                .description("Hos Api文档")
                .termsOfServiceUrl("https://kndhos.com/knd/hos")
                .contact(new Contact("dev", "https://kndhos.com/knd/guns", ""))
                .version("2.0")
                .build();
    }
    private List<ApiKey> securitySchemes() {
        return new ArrayList<ApiKey>(){{
            add(new ApiKey("deviceCode", "deviceCode", "header"));
        }};
    }

    private List<SecurityContext> securityContexts() {
        return new ArrayList<SecurityContext>(){{
            add(SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.any())
                    .build());
        }};
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("deviceCode", authorizationScopes));
    }

}

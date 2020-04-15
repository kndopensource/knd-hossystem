package com.hos.admin.config.web;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.hos.base.consts.DefContants;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * swagger配置类
 *
 * @author 众神
 * @date 2017年6月1日19:42:59
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig implements WebMvcConfigurer{

    /**
     *
     * 显示swagger-ui.html文档展示页，还必须注入swagger资源：
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * 可以进行分组，以value区别。不同组可以定制化接口限定条件
     *
     * @return Docket
     */
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
//                .securitySchemes(Collections.singletonList(securityScheme()))
                // 配置swagger接口安全校验上下文中的信息（包含安全权限与安全校验生效的接口路径）
//                .securityContexts(securityContexts())
                .apiInfo(apiInfo())
                //.globalOperationParameters(setHeaderToken());
                // swagger生效
                .enable(true);
    }
    /**
     * api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // //大标题
                .title("Hos.dev 后台服务API接口文档")
                // 版本号
                .version("1.0")
                // 描述
                .description("快速开发系统后台API接口")
                // 作者
                .contact("KND团队")
//                .termsOfServiceUrl("https://kndhos.com/knd/hos")
//                .contact(new Contact("dev", "https://kndhos.com/knd/guns", ""))
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }




    /***
     * oauth2配置
     * 需要增加swagger授权回调地址
     * http://localhost:8888/webjars/springfox-swagger-ui/o2c.html
     * @return
     */
//    @Bean
//    SecurityScheme securityScheme() {
//        return new ApiKey(DefContants.X_ACCESS_TOKEN, DefContants.X_ACCESS_TOKEN, "header");
//    }
//    /**
//     * JWT token
//     * @return
//     */
//    private List<Parameter> setHeaderToken() {
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name(DefContants.X_ACCESS_TOKEN).description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
//        return pars;
//    }

    /**
     * ==============自定义校验规则============
     * @return
     */
//    private List<ApiKey> securitySchemes() {
//        return new ArrayList<ApiKey>(){{
//            add(new ApiKey("deviceCode", "deviceCode", "header"));
//        }};
//    }
//
//    private List<SecurityContext> securityContexts() {
//        return new ArrayList<SecurityContext>(){{
//            add(SecurityContext.builder()
//                    .securityReferences(defaultAuth())
//                    .forPaths(PathSelectors.any())
//                    .build());
//        }};
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(
//                new SecurityReference("deviceCode", authorizationScopes));
//    }
    /**
     * ==============自定义校验规则============
     * @return
     */

}

package com.hos.admin.config.web;

import com.hos.base.auth.context.LoginContext;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web 配置类
 *
 * @author 众神
 * @date 2016年11月12日 下午5:03:32
 */
//@Configuration
//public class BeetlConfig {
//
//    @Autowired
//    private BeetlProperties beetlProperties;
//
//    /**
//     * beetl的配置
//     */
//    @Bean(initMethod = "init")
//    public BeetlConfiguration beetlConfiguration(LoginContext loginContext) {
//        BeetlConfiguration beetlConfiguration = new BeetlConfiguration(loginContext);
//        beetlConfiguration.setResourceLoader(new ClasspathResourceLoader(BeetlConfig.class.getClassLoader(), beetlProperties.getPrefix()));
//        beetlConfiguration.setConfigProperties(beetlProperties.getProperties());
//        return beetlConfiguration;
//    }
//
//    /**
//     * beetl的视图解析器
//     */
//    @Bean
//    public BeetlSpringViewResolver beetlViewResolver(LoginContext loginContext) {
//        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
//        beetlSpringViewResolver.setConfig(beetlConfiguration(loginContext));
//        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
//        beetlSpringViewResolver.setOrder(0);
//        return beetlSpringViewResolver;
//    }
//}

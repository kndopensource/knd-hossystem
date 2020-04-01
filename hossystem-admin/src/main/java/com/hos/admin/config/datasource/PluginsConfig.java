package com.hos.admin.config.datasource;

import cn.stylefeng.roses.core.metadata.CustomMetaObjectHandler;
import com.hos.base.auth.context.LoginContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp的插件拓展和资源扫描
 *
 * @author 众神
 * @Date 2019/5/10 21:33
 */
@Configuration
@MapperScan(basePackages = {"com.hos.**.mapper"})
public class PluginsConfig {

    /**
     * 拓展核心包中的字段包装器
     *
     * @author 众神
     * @Date 2019/5/10 21:35
     */
    @Bean
    public CustomMetaObjectHandler gunsMpFieldHandler() {
        return new CustomMetaObjectHandler() {

            @Override
            protected Long getUserUniqueId() {
                try {
                    return LoginContextHolder.getContext().getUser().getId();
                } catch (Exception e) {

                    //如果获取不到当前用户就存空id
                    return -100L;
                }
            }
        };
    }

}

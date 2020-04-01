package com.hos;

import cn.stylefeng.roses.core.config.MybatisDataSourceAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot方式启动类
 *
 * @author dev
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication(exclude = {MybatisDataSourceAutoConfiguration.class})
public class HosApplication {

    private final static Logger logger = LoggerFactory.getLogger(HosApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HosApplication.class, args);
        logger.info(HosApplication.class.getSimpleName() + " is success!");
    }

}

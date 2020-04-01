package com.hos.base.db.factory;

import com.hos.base.db.entity.DatabaseInfo;
import cn.stylefeng.roses.core.config.properties.DruidProperties;

/**
 * 配置文件的创建
 *
 * @author hossystem
 * @date 2019-06-15-20:05
 */
public class DruidFactory {

    /**
     * 创建druid配置
     *
     * @author hossystem
     * @Date 2019-06-15 20:05
     */
    public static DruidProperties createDruidProperties(DatabaseInfo databaseInfo) {

        DruidProperties druidProperties = new DruidProperties();

        druidProperties.setDriverClassName(databaseInfo.getJdbcDriver());
        druidProperties.setUsername(databaseInfo.getUserName());
        druidProperties.setPassword(databaseInfo.getPassword());
        druidProperties.setUrl(databaseInfo.getJdbcUrl());

        return druidProperties;

    }

}

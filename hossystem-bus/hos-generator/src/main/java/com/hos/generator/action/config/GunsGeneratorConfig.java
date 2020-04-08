package com.hos.generator.action.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 默认的代码生成的配置
 *
 * @author knddev
 * @date 2017-10-28-下午8:27
 */
public class GunsGeneratorConfig extends AbstractGeneratorConfig {

    protected void globalConfig() {
        globalConfig.setOutputDir("D:\\ttt");//写自己项目的绝对路径,注意具体到java目录
        globalConfig.setFileOverride(true);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor("zt");
    }

    protected void dataSourceConfig() {
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUrl("jdbc:mysql://192.168.1.110:3306/kndcollectdb?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT");
    }

    protected void strategyConfig() {
        strategyConfig.setTablePrefix(new String[]{"bus_"});// 此处可以修改为您的表前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
    }

    protected void packageConfig() {
        packageConfig.setParent(null);
        packageConfig.setEntity("com.hos.coltransfer.modular.entity");
        packageConfig.setMapper("com.hos.coltransfer.modular.mapper");
        packageConfig.setXml("com.hos.coltransfer.modular.mapper.mapping");
    }

    protected void contextConfig() {
        contextConfig.setProPackage("com.hos.coltransfer");
        contextConfig.setCoreBasePackage("com.hos.coltransfer.core");
        contextConfig.setBizChName("数据采集管理");
        contextConfig.setBizEnName("coltransfer");
        contextConfig.setModuleName("coltransfer");
        contextConfig.setProjectPath("H:\\workspace\\knd-hossystem\\hossystem-bus\\hos-data-coltransfer");//写自己项目的绝对路径
        contextConfig.setEntityName("BusTaskInfo");
        sqlConfig.setParentMenuName(null);//这里写已有菜单的名称,当做父节点

        /**
         * mybatis-plus 生成器开关
         */
        contextConfig.setEntitySwitch(true);
        contextConfig.setDaoSwitch(true);
        contextConfig.setServiceSwitch(true);

        /**
         * guns 生成器开关
         */
        contextConfig.setControllerSwitch(true);
        contextConfig.setIndexPageSwitch(true);
        contextConfig.setAddPageSwitch(true);
        contextConfig.setEditPageSwitch(true);
        contextConfig.setJsSwitch(true);
        contextConfig.setInfoJsSwitch(true);
        contextConfig.setSqlSwitch(true);
    }

    @Override
    protected void config() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        packageConfig();
        contextConfig();
    }
}

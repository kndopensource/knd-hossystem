package com.hos.sys.core.properties;

import lombok.Data;

/**
 * guns项目配置
 *
 * @author dev
 * @Date 2017/5/23 22:31
 */
@Data
public class GunsProperties {

    public static final String PREFIX = "guns";

    private Boolean springSessionOpen = false;

    /**
     * session 失效时间（默认为30分钟 单位：秒）
     */
    private Integer sessionInvalidateTime = 30 * 60;

    /**
     * session 验证失效时间（默认为15分钟 单位：秒）
     */
    private Integer sessionValidationInterval = 15 * 60;

}

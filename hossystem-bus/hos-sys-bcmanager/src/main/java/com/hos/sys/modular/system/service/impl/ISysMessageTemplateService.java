package com.hos.sys.modular.system.service.impl;

import com.hos.base.system.base.service.JeecgService;
import com.hos.sys.modular.system.entity.SysMessageTemplate;

import java.util.List;


/**
 * @Description: 消息模板
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends JeecgService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}

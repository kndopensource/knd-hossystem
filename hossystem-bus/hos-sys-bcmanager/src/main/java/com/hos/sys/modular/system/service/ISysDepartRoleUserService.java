package com.hos.sys.modular.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hos.sys.modular.system.entity.SysDepartRoleUser;

/**
 * @Description: 部门角色人员信息
 * @Author: jeecg-boot
 * @Date:   2020-02-13
 * @Version: V1.0
 */
public interface ISysDepartRoleUserService extends IService<SysDepartRoleUser> {

    void deptRoleUserAdd(String userId, String newRoleId, String oldRoleId);
}

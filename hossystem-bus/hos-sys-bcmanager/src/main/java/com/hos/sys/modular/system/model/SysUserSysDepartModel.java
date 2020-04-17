package com.hos.sys.modular.system.model;

import com.hos.sys.modular.system.entity.SysDepart;
import com.hos.sys.modular.system.entity.SysUser;
import lombok.Data;

/**
 * 包含 SysUser 和 SysDepart 的 Model
 *
 * @author zt
 */
@Data
public class SysUserSysDepartModel {

    private SysUser sysUser;
    private SysDepart sysDepart;

}

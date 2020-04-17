package com.hos.sys.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hos.sys.modular.system.entity.SysUserDepart;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface SysUserDepartMapper extends BaseMapper<SysUserDepart> {
	
	List<SysUserDepart> getUserDepartByUid(@Param("userId") String userId);
}

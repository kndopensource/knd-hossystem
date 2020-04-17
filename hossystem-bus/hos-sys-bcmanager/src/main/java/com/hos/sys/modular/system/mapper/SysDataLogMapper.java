package com.hos.sys.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hos.sys.modular.system.entity.SysDataLog;
import org.apache.ibatis.annotations.Param;

public interface SysDataLogMapper extends BaseMapper<SysDataLog> {
	/**
	 * 通过表名及数据Id获取最大版本
	 * @param tableName
	 * @param dataId
	 * @return
	 */
	public String queryMaxDataVer(@Param("tableName") String tableName, @Param("dataId") String dataId);
	
}

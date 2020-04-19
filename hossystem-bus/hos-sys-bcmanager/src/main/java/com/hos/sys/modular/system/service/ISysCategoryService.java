package com.hos.sys.modular.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hos.sys.modular.system.entity.SysCategory;
import com.hos.sys.modular.system.model.TreeSelectModel;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 分类字典
 * @Author: jeecg-boot
 * @Date:   2019-05-29
 * @Version: V1.0
 */
public interface ISysCategoryService extends IService<SysCategory> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";

	void addSysCategory(SysCategory sysCategory);
	
	void updateSysCategory(SysCategory sysCategory);
	
	/**
	  * 根据父级编码加载分类字典的数据
	 * @param pcode
	 * @return
	 */
	public List<TreeSelectModel> queryListByCode(String pcode) throws ServerException;
	
	/**
	  * 根据pid查询子节点集合
	 * @param pid
	 * @return
	 */
	public List<TreeSelectModel> queryListByPid(String pid);

	/**
	 * 根据pid查询子节点集合,支持查询条件
	 * @param pid
	 * @param condition
	 * @return
	 */
	public List<TreeSelectModel> queryListByPid(String pid, Map<String, String> condition);

	/**
	 * 根据code查询id
	 * @param code
	 * @return
	 */
	public String queryIdByCode(String code);
	
}

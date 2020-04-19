package com.hos.sys.modular.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hos.sys.modular.system.entity.SysRole;
import com.hos.sys.modular.system.entity.SysUser;
import com.hos.sys.modular.system.entity.SysUserRole;
import com.hos.sys.modular.system.mapper.SysUserRoleMapper;
import com.hos.sys.modular.system.service.ISysRoleService;
import com.hos.sys.modular.system.service.ISysUserRoleService;
import com.hos.sys.modular.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

	@Autowired
	private ISysUserService userService;
	@Autowired
	private ISysRoleService roleService;
	
	/**
	 * 查询所有用户对应的角色信息
	 */
	@Override
	public Map<String,String> queryUserRole() {
		List<SysUserRole> uRoleList = this.list();
		List<SysUser> userList = userService.list();
		List<SysRole> roleList = roleService.list();
		Map<String,String> map = new IdentityHashMap<>();
		String userId = "";
		String roleId = "";
		String roleName = "";
		if(uRoleList != null && uRoleList.size() > 0) {
			for(SysUserRole uRole : uRoleList) {
				roleId = uRole.getRoleId();
				for(SysUser user : userList) {
					userId = user.getId();
					if(uRole.getUserId().equals(userId)) {
						roleName = this.searchByRoleId(roleList,roleId);
						map.put(userId, roleName);
					}
				}
			}
			return map;
		}
		return map;
	}
	
	/**
	 * queryUserRole调用的方法
	 * @param roleList
	 * @param roleId
	 * @return
	 */
	private String searchByRoleId(List<SysRole> roleList, String roleId) {
		while(true) {
			for(SysRole role : roleList) {
				if(roleId.equals(role.getId())) {
					return role.getRoleName();
				}
			}
		}
	}

}

package com.hos.sys.modular.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hos.base.util.oConvertUtils;
import com.hos.sys.modular.system.entity.SysDepartPermission;
import com.hos.sys.modular.system.entity.SysPermissionDataRule;
import com.hos.sys.modular.system.mapper.SysDepartPermissionMapper;
import com.hos.sys.modular.system.mapper.SysPermissionDataRuleMapper;
import com.hos.sys.modular.system.service.ISysDepartPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: 部门权限表
 * @Author: jeecg-boot
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Service
public class SysDepartPermissionServiceImpl extends ServiceImpl<SysDepartPermissionMapper, SysDepartPermission> implements ISysDepartPermissionService {
    @Resource
    private SysPermissionDataRuleMapper ruleMapper;

    @Override
    public void saveDepartPermission(String departId, String permissionIds, String lastPermissionIds) {
        List<String> add = getDiff(lastPermissionIds,permissionIds);
        if(add!=null && add.size()>0) {
            List<SysDepartPermission> list = new ArrayList<SysDepartPermission>();
            for (String p : add) {
                if(oConvertUtils.isNotEmpty(p)) {
                    SysDepartPermission rolepms = new SysDepartPermission(departId, p);
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }
        List<String> delete = getDiff(permissionIds,lastPermissionIds);
        if(delete!=null && delete.size()>0) {
            for (String permissionId : delete) {
                this.remove(new QueryWrapper<SysDepartPermission>().lambda().eq(SysDepartPermission::getDepartId, departId).eq(SysDepartPermission::getPermissionId, permissionId));
            }
        }
    }

    @Override
    public List<SysPermissionDataRule> getPermRuleListByDeptIdAndPermId(String departId, String permissionId) {
        SysDepartPermission departPermission = this.getOne(new QueryWrapper<SysDepartPermission>().lambda().eq(SysDepartPermission::getDepartId, departId).eq(SysDepartPermission::getPermissionId, permissionId));
        if(departPermission != null){
            LambdaQueryWrapper<SysPermissionDataRule> query = new LambdaQueryWrapper<SysPermissionDataRule>();
            query.in(SysPermissionDataRule::getId, Arrays.asList(departPermission.getDataRuleIds().split(",")));
            query.orderByDesc(SysPermissionDataRule::getCreateTime);
            List<SysPermissionDataRule> permRuleList = this.ruleMapper.selectList(query);
            return permRuleList;
        }else{
            return null;
        }
    }

    /**
     * 从diff中找出main中没有的元素
     * @param main
     * @param diff
     * @return
     */
    private List<String> getDiff(String main,String diff){
        if(oConvertUtils.isEmpty(diff)) {
            return null;
        }
        if(oConvertUtils.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }

        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<String>();
        for (String key : diffArr) {
            if(oConvertUtils.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }
}

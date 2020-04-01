package com.hos.sys.core.constant.dictmap;

import com.hos.base.dict.AbstractDictMap;

/**
 * 部门的映射
 *
 * @author 众神
 * @date 2017-05-06 15:01
 */
public class DeptDict extends AbstractDictMap {

    @Override
    public void init() {
        put("deptId", "部门名称");
        put("sort", "部门排序");
        put("pid", "上级名称");
        put("simpleName", "部门简称");
        put("fullName", "部门全称");
        put("description", "备注");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("deptId", "getDeptName");
        putFieldWrapperMethodName("pid", "getDeptName");
    }
}

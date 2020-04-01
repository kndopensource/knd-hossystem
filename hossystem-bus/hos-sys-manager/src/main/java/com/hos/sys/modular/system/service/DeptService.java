package com.hos.sys.modular.system.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hos.base.pojo.node.LayuiTreeNode;
import com.hos.base.pojo.node.TreeviewNode;
import com.hos.base.pojo.node.ZTreeNode;
import com.hos.base.pojo.page.LayuiPageFactory;
import com.hos.sys.core.exception.enums.BizExceptionEnum;
import com.hos.sys.modular.system.entity.Dept;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hos.sys.modular.system.mapper.DeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author dev
 * @since 2018-12-07
 */
@Service
public class DeptService extends ServiceImpl<DeptMapper, Dept> {

    @Resource
    private DeptMapper deptMapper;

    /**
     * 新增部门
     *
     * @author 众神
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDept(Dept dept) {

        if (ToolUtil.isOneEmpty(dept, dept.getSimpleName(), dept.getFullName(), dept.getPid())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);

        this.save(dept);
    }

    /**
     * 修改部门
     *
     * @author 众神
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editDept(Dept dept) {

        if (ToolUtil.isOneEmpty(dept, dept.getDeptId(), dept.getSimpleName(), dept.getFullName(), dept.getPid())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);

        this.updateById(dept);
    }

    /**
     * 删除部门
     *
     * @author 众神
     * @Date 2018/12/23 5:16 PM
     */
    @Transactional
    public void deleteDept(Long deptId) {
        Dept dept = deptMapper.selectById(deptId);

        //根据like查询删除所有级联的部门
        List<Dept> subDepts = deptMapper.likePids(dept.getDeptId());

        for (Dept temp : subDepts) {
            this.removeById(temp.getDeptId());
        }

        this.removeById(dept.getDeptId());
    }

    /**
     * 获取ztree的节点列表
     *
     * @author 众神
     * @Date 2018/12/23 5:16 PM
     */
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    /**
     * 获取layuiTree的节点列表
     *
     * @author 众神
     * @Date 2019-8-27 15:23
     */
    public List<LayuiTreeNode> layuiTree() {
        return this.baseMapper.layuiTree();
    }

    /**
     * 获取ztree的节点列表
     *
     * @author 众神
     * @Date 2018/12/23 5:16 PM
     */
    public List<TreeviewNode> treeviewNodes() {
        return this.baseMapper.treeviewNodes();
    }

    /**
     * 获取所有部门列表
     *
     * @author 众神
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(String condition, Long deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, condition, deptId);
    }

    /**
     * 设置部门的父级ids
     *
     * @author 众神
     * @Date 2018/12/23 4:58 PM
     */
    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0L)) {
            dept.setPid(0L);
            dept.setPids("[0],");
        } else {
            Long pid = dept.getPid();
            Dept temp = this.getById(pid);
            String pids = temp.getPids();
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }
}

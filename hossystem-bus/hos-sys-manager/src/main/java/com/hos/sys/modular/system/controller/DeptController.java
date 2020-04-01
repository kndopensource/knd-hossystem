package com.hos.sys.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hos.base.auth.annotion.Permission;
import com.hos.base.log.BussinessLog;
import com.hos.base.pojo.node.LayuiTreeNode;
import com.hos.base.pojo.node.TreeviewNode;
import com.hos.base.pojo.node.ZTreeNode;
import com.hos.base.pojo.page.LayuiPageFactory;
import com.hos.sys.core.constant.dictmap.DeptDict;
import com.hos.sys.core.constant.factory.ConstantFactory;
import com.hos.sys.core.log.LogObjectHolder;
import com.hos.sys.modular.system.entity.Dept;
import com.hos.sys.modular.system.factory.LayuiTreeFactory;
import com.hos.sys.modular.system.model.DeptDto;
import com.hos.sys.modular.system.service.DeptService;
import com.hos.sys.modular.system.warpper.DeptTreeWrapper;
import com.hos.sys.modular.system.warpper.DeptWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 部门控制器
 *
 * @author 众神
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private String PREFIX = "/modular/system/dept/";

    @Autowired
    private DeptService deptService;

    /**
     * 跳转到部门管理首页
     *
     * @author 众神
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dept.html";
    }

    /**
     * 跳转到添加部门
     *
     * @author 众神
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/dept_add")
    public String deptAdd() {
        return PREFIX + "dept_add.html";
    }

    /**
     * 跳转到修改部门
     *
     * @author 众神
     * @Date 2018/12/23 4:56 PM
     */
    @Permission
    @RequestMapping("/dept_update")
    public String deptUpdate(@RequestParam("deptId") Long deptId) {
        Dept dept = deptService.getById(deptId);
        LogObjectHolder.me().set(dept);

        return PREFIX + "dept_edit.html";
    }

    /**
     * 获取部门的tree列表，layuiTree格式
     *
     * @author 众神
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping(value = "/layuiTree")
    @ResponseBody
    public List<LayuiTreeNode> layuiTree() {

        List<LayuiTreeNode> list = this.deptService.layuiTree();
        list.add(LayuiTreeFactory.createRoot());

        DefaultTreeBuildFactory<LayuiTreeNode> treeBuildFactory = new DefaultTreeBuildFactory<>();
        treeBuildFactory.setRootParentId("-1");
        return treeBuildFactory.doTreeBuild(list);
    }

    /**
     * 获取部门的tree列表，ztree格式
     *
     * @author 众神
     * @Date 2019-8-27 15:24
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 获取部门的tree列表，treeview格式
     *
     * @author 众神
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/treeview")
    @ResponseBody
    public List<TreeviewNode> treeview() {
        List<TreeviewNode> treeviewNodes = this.deptService.treeviewNodes();

        //构建树
        DefaultTreeBuildFactory<TreeviewNode> factory = new DefaultTreeBuildFactory<>();
        factory.setRootParentId("0");
        List<TreeviewNode> results = factory.doTreeBuild(treeviewNodes);

        //把子节点为空的设为null
        DeptTreeWrapper.clearNull(results);

        return results;
    }

    /**
     * 新增部门
     *
     * @author 众神
     * @Date 2018/12/23 4:57 PM
     */
    @BussinessLog(value = "添加部门", key = "simpleName", dict = DeptDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public ResponseData add(@Valid Dept dept) {
        this.deptService.addDept(dept);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有部门列表
     *
     * @author 众神
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "deptId", required = false) Long deptId) {
        Page<Map<String, Object>> list = this.deptService.list(condition, deptId);
        Page<Map<String, Object>> wrap = new DeptWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 部门详情
     *
     * @author 众神
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/detail/{deptId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("deptId") Long deptId) {
        Dept dept = deptService.getById(deptId);
        DeptDto deptDto = new DeptDto();
        BeanUtil.copyProperties(dept, deptDto);
        deptDto.setPName(ConstantFactory.me().getDeptName(deptDto.getPid()));
        return deptDto;
    }

    /**
     * 修改部门
     *
     * @author 众神
     * @Date 2018/12/23 4:57 PM
     */
    @BussinessLog(value = "修改部门", key = "simpleName", dict = DeptDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public ResponseData update(@Valid Dept dept) {
        deptService.editDept(dept);
        return SUCCESS_TIP;
    }

    /**
     * 删除部门
     *
     * @author 众神
     * @Date 2018/12/23 4:57 PM
     */
    @BussinessLog(value = "删除部门", key = "deptId", dict = DeptDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam("deptId") Long deptId) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));

        deptService.deleteDept(deptId);

        return SUCCESS_TIP;
    }

}

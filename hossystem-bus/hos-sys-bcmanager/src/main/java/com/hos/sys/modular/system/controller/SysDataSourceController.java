package com.hos.sys.modular.system.controller;

import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hos.base.aspect.annotation.AutoLog;
import com.hos.base.system.query.QueryGenerator;
import com.hos.sys.modular.system.entity.SysDataSource;
import com.hos.sys.modular.system.service.ISysDataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 多数据源管理
 * @Author: jeecg-boot
 * @Date: 2019-12-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "多数据源管理")
@RestController
@RequestMapping("/sys/dataSource")
public class SysDataSourceController extends JeecgController<SysDataSource, ISysDataSourceService> {

    @Autowired
    private ISysDataSourceService sysDataSourceService;

    /**
     * 分页列表查询
     *
     * @param sysDataSource
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "多数据源管理-分页列表查询")
    @ApiOperation(value = "多数据源管理-分页列表查询", notes = "多数据源管理-分页列表查询")
    @GetMapping(value = "/list")
    public ResponseData queryPageList(
            SysDataSource sysDataSource,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            HttpServletRequest req
    ) {
        QueryWrapper<SysDataSource> queryWrapper = QueryGenerator.initQueryWrapper(sysDataSource, req.getParameterMap());
        Page<SysDataSource> page = new Page<>(pageNo, pageSize);
        IPage<SysDataSource> pageList = sysDataSourceService.page(page, queryWrapper);
        return ResponseData.success(pageList);
    }

    @GetMapping(value = "/options")
    public ResponseData queryOptions(SysDataSource sysDataSource, HttpServletRequest req) {
        QueryWrapper<SysDataSource> queryWrapper = QueryGenerator.initQueryWrapper(sysDataSource, req.getParameterMap());
        List<SysDataSource> pageList = sysDataSourceService.list(queryWrapper);
        JSONArray array = new JSONArray(pageList.size());
        for (SysDataSource item : pageList) {
            JSONObject option = new JSONObject(3);
            option.put("value", item.getCode());
            option.put("label", item.getName());
            option.put("text", item.getName());
            array.add(option);
        }
        return ResponseData.success(array);
    }

    /**
     * 添加
     *
     * @param sysDataSource
     * @return
     */
    @AutoLog(value = "多数据源管理-添加")
    @ApiOperation(value = "多数据源管理-添加", notes = "多数据源管理-添加")
    @PostMapping(value = "/add")
    public ResponseData add(@RequestBody SysDataSource sysDataSource) {
        sysDataSourceService.save(sysDataSource);
        return ResponseData.success("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sysDataSource
     * @return
     */
    @AutoLog(value = "多数据源管理-编辑")
    @ApiOperation(value = "多数据源管理-编辑", notes = "多数据源管理-编辑")
    @PutMapping(value = "/edit")
    public ResponseData edit(@RequestBody SysDataSource sysDataSource) {
        sysDataSourceService.updateById(sysDataSource);
//        DataSourceCachePool.removeCacheById(sysDataSource.getId());
        return ResponseData.success("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "多数据源管理-通过id删除")
    @ApiOperation(value = "多数据源管理-通过id删除", notes = "多数据源管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public ResponseData delete(@RequestParam(name = "id") String id) {
        sysDataSourceService.removeById(id);
//        DataSourceCachePool.removeCacheById(id);
        return ResponseData.success("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "多数据源管理-批量删除")
    @ApiOperation(value = "多数据源管理-批量删除", notes = "多数据源管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData deleteBatch(@RequestParam(name = "ids") String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        this.sysDataSourceService.removeByIds(idList);
//        idList.forEach(DataSourceCachePool::removeCacheById);
        return ResponseData.success("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "多数据源管理-通过id查询")
    @ApiOperation(value = "多数据源管理-通过id查询", notes = "多数据源管理-通过id查询")
    @GetMapping(value = "/queryById")
    public ResponseData queryById(@RequestParam(name = "id") String id) {
        SysDataSource sysDataSource = sysDataSourceService.getById(id);
        return ResponseData.success(sysDataSource);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param sysDataSource
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SysDataSource sysDataSource) {
        return super.exportXls(request, sysDataSource, SysDataSource.class, "多数据源管理");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public ResponseData importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SysDataSource.class);
    }

}

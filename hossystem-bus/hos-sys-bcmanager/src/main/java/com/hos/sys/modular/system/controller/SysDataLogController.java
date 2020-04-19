package com.hos.sys.modular.system.controller;


import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hos.base.system.query.QueryGenerator;
import com.hos.sys.modular.system.entity.SysDataLog;
import com.hos.sys.modular.system.service.ISysDataLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sys/dataLog")
@Slf4j
public class SysDataLogController {
	@Autowired
	private ISysDataLogService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseData queryPageList(SysDataLog dataLog, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {

		QueryWrapper<SysDataLog> queryWrapper = QueryGenerator.initQueryWrapper(dataLog, req.getParameterMap());
		Page<SysDataLog> page = new Page<SysDataLog>(pageNo, pageSize);
		IPage<SysDataLog> pageList = service.page(page, queryWrapper);
		log.info("查询当前页："+pageList.getCurrent());
		log.info("查询当前页数量："+pageList.getSize());
		log.info("查询结果数量："+pageList.getRecords().size());
		log.info("数据总数："+pageList.getTotal());

		return ResponseData.success(pageList);
	}
	
	/**
	 * 查询对比数据
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryCompareList", method = RequestMethod.GET)
	public ResponseData queryCompareList(HttpServletRequest req) {
		String dataId1 = req.getParameter("dataId1");
		String dataId2 = req.getParameter("dataId2");
		List<String> idList = new ArrayList<String>();
		idList.add(dataId1);
		idList.add(dataId2);
		try {
			List<SysDataLog> list =  (List<SysDataLog>) service.listByIds(idList);
         return ResponseData.success(list);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		 return	ResponseData.error(e.getMessage());
		}
	}
	
	/**
	 * 查询版本信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryDataVerList", method = RequestMethod.GET)
	public ResponseData queryDataVerList(HttpServletRequest req) {
		String dataTable = req.getParameter("dataTable");
		String dataId = req.getParameter("dataId");
		QueryWrapper<SysDataLog> queryWrapper = new QueryWrapper<SysDataLog>();
		queryWrapper.eq("data_table", dataTable);
		queryWrapper.eq("data_id", dataId);
		List<SysDataLog> list = service.list(queryWrapper);
		if(list==null||list.size()<=0) {
			return ResponseData.error("未找到版本信息");
		}else {
			return ResponseData.success(list);
		}
	}
	
}

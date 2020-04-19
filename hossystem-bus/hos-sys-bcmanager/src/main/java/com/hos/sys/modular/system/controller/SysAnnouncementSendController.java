package com.hos.sys.modular.system.controller;

import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hos.base.consts.CommonConstant;
import com.hos.base.util.oConvertUtils;
import com.hos.sys.modular.system.entity.SysAnnouncementSend;
import com.hos.sys.modular.system.model.AnnouncementSendModel;
import com.hos.sys.modular.system.service.ISysAnnouncementSendService;
import com.hos.sys.modular.system.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.Arrays;
import java.util.Date;

/**
* @Title: Controller
* @Description: 用户通告阅读标记表
* @Author: jeecg-boot
* @Date:  2019-02-21
* @Version: V1.0
*/
@RestController
@RequestMapping("/sys/sysAnnouncementSend")
@Slf4j
public class SysAnnouncementSendController {
   @Autowired
   private ISysAnnouncementSendService sysAnnouncementSendService;

   /**
     * 分页列表查询
    * @param sysAnnouncementSend
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @GetMapping(value = "/list")
   public ResponseData queryPageList(SysAnnouncementSend sysAnnouncementSend,
                                     @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                     HttpServletRequest req) {
       QueryWrapper<SysAnnouncementSend> queryWrapper = new QueryWrapper<SysAnnouncementSend>(sysAnnouncementSend);
       Page<SysAnnouncementSend> page = new Page<SysAnnouncementSend>(pageNo,pageSize);
       //排序逻辑 处理
       String column = req.getParameter("column");
       String order = req.getParameter("order");
       if(oConvertUtils.isNotEmpty(column) && oConvertUtils.isNotEmpty(order)) {
           if("asc".equals(order)) {
               queryWrapper.orderByAsc(oConvertUtils.camelToUnderline(column));
           }else {
               queryWrapper.orderByDesc(oConvertUtils.camelToUnderline(column));
           }
       }
       IPage<SysAnnouncementSend> pageList = sysAnnouncementSendService.page(page, queryWrapper);
       //log.info("查询当前页："+pageList.getCurrent());
       //log.info("查询当前页数量："+pageList.getSize());
       //log.info("查询结果数量："+pageList.getRecords().size());
       //log.info("数据总数："+pageList.getTotal());
       return ResponseData.success(pageList);
   }

   /**
     *   添加
    * @param sysAnnouncementSend
    * @return
    */
   @PostMapping(value = "/add")
   public ResponseData add(@RequestBody SysAnnouncementSend sysAnnouncementSend) {
       try {
           sysAnnouncementSendService.save(sysAnnouncementSend);
           return ResponseData.success("添加成功!");
       } catch (Exception e) {
           log.error(e.getMessage(),e);
           return ResponseData.error("操作失败!");
       }
   }

   /**
     *  编辑
    * @param sysAnnouncementSend
    * @return
    */
   @PutMapping(value = "/edit")
   public ResponseData eidt(@RequestBody SysAnnouncementSend sysAnnouncementSend) {
       SysAnnouncementSend sysAnnouncementSendEntity = sysAnnouncementSendService.getById(sysAnnouncementSend.getId());
       if(sysAnnouncementSendEntity==null) {
           return ResponseData.error("未找到对应实体");
       }else {
           boolean ok = sysAnnouncementSendService.updateById(sysAnnouncementSend);
           //TODO 返回false说明什么？
           if(ok) {
               return ResponseData.success("修改成功!");
           }
           return ResponseData.error("修改失败!");
       }
   }

   /**
     *   通过id删除
    * @param id
    * @return
    */
   @DeleteMapping(value = "/delete")
   public ResponseData delete(@RequestParam(name="id",required=true) String id) {
       SysAnnouncementSend sysAnnouncementSend = sysAnnouncementSendService.getById(id);
       if(sysAnnouncementSend==null) {
          return ResponseData.error("未找到对应实体");
       }else {
           boolean ok = sysAnnouncementSendService.removeById(id);
           if(ok) {
               return ResponseData.success("删除成功!");
           }
           return ResponseData.success("删除失败!");
       }
   }

   /**
     *  批量删除
    * @param ids
    * @return
    */
   @DeleteMapping(value = "/deleteBatch")
   public ResponseData deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       if(ids==null || "".equals(ids.trim())) {
           return ResponseData.error("参数不识别！");
       }else {
           this.sysAnnouncementSendService.removeByIds(Arrays.asList(ids.split(",")));
           return ResponseData.success("删除成功!");
       }
   }

   /**
     * 通过id查询
    * @param id
    * @return
    */
   @GetMapping(value = "/queryById")
   public ResponseData queryById(@RequestParam(name="id",required=true) String id) {
       SysAnnouncementSend sysAnnouncementSend = sysAnnouncementSendService.getById(id);
       if(sysAnnouncementSend==null) {
           return ResponseData.error("未找到对应实体");
       }else {
           return ResponseData.success(sysAnnouncementSend);
       }
   }

   /**
    * @功能：更新用户系统消息阅读状态
    * @param json
    * @return
    */
   @PutMapping(value = "/editByAnntIdAndUserId")
   public ResponseData editById(@RequestBody JSONObject json) {
       String anntId = json.getString("anntId");
//       LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
       LoginUser sysUser = new LoginUser();
       String userId = sysUser.getId();
       LambdaUpdateWrapper<SysAnnouncementSend> updateWrapper = new UpdateWrapper().lambda();
       updateWrapper.set(SysAnnouncementSend::getReadFlag, CommonConstant.HAS_READ_FLAG);
       updateWrapper.set(SysAnnouncementSend::getReadTime, new Date());
       updateWrapper.last("where annt_id ='"+anntId+"' and user_id ='"+userId+"'");
       SysAnnouncementSend announcementSend = new SysAnnouncementSend();
       sysAnnouncementSendService.update(announcementSend, updateWrapper);
       return ResponseData.success();
   }

   /**
    * @功能：获取我的消息
    * @return
    */
   @GetMapping(value = "/getMyAnnouncementSend")
   public ResponseData getMyAnnouncementSend(AnnouncementSendModel announcementSendModel,
                                                                     @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
//       LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
       LoginUser sysUser = new LoginUser();
       String userId = sysUser.getId();
       announcementSendModel.setUserId(userId);
       announcementSendModel.setPageNo((pageNo-1)*pageSize);
       announcementSendModel.setPageSize(pageSize);
       Page<AnnouncementSendModel> pageList = new Page<AnnouncementSendModel>(pageNo,pageSize);
       pageList = sysAnnouncementSendService.getMyAnnouncementSendPage(pageList, announcementSendModel);
       return ResponseData.success(pageList);
   }

   /**
    * @功能：一键已读
    * @return
    */
   @PutMapping(value = "/readAll")
   public ResponseData readAll() {
//       LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
       LoginUser sysUser = new LoginUser();
       String userId = sysUser.getId();
       LambdaUpdateWrapper<SysAnnouncementSend> updateWrapper = new UpdateWrapper().lambda();
       updateWrapper.set(SysAnnouncementSend::getReadFlag, CommonConstant.HAS_READ_FLAG);
       updateWrapper.set(SysAnnouncementSend::getReadTime, new Date());
       updateWrapper.last("where user_id ='"+userId+"'");
       SysAnnouncementSend announcementSend = new SysAnnouncementSend();
       sysAnnouncementSendService.update(announcementSend, updateWrapper);

       return ResponseData.success(ResponseData.DEFAULT_SUCCESS_CODE,"全部已读",null);
   }
}

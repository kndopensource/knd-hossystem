package com.hos.sys.modular.system.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hos.base.system.query.QueryGenerator;
import com.hos.base.system.vo.DictModel;
import com.hos.base.system.vo.LoginUser;
import com.hos.base.util.oConvertUtils;
import com.hos.sys.modular.system.entity.SysCategory;
import com.hos.sys.modular.system.model.TreeSelectModel;
import com.hos.sys.modular.system.service.ISysCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @Description: 分类字典
* @Author: jeecg-boot
* @Date:   2019-05-29
* @Version: V1.0
*/
@RestController
@RequestMapping("/sys/category")
@Slf4j
public class SysCategoryController {
   @Autowired
   private ISysCategoryService sysCategoryService;

   /**
     * 分页列表查询
    * @param sysCategory
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @GetMapping(value = "/rootList")
   public ResponseData queryPageList(SysCategory sysCategory,
                                     @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                     HttpServletRequest req) {
       if(oConvertUtils.isEmpty(sysCategory.getPid())){
           sysCategory.setPid("0");
       }

       //QueryWrapper<SysCategory> queryWrapper = QueryGenerator.initQueryWrapper(sysCategory, req.getParameterMap());
       QueryWrapper<SysCategory> queryWrapper = new QueryWrapper<SysCategory>();
       queryWrapper.eq("pid", sysCategory.getPid());
       //--author:os_chengtgen---date:20190804 -----for: 分类字典页面显示错误,issues:377--------end

       Page<SysCategory> page = new Page<SysCategory>(pageNo, pageSize);
       IPage<SysCategory> pageList = sysCategoryService.page(page, queryWrapper);
       return ResponseData.success(pageList);
   }

   @GetMapping(value = "/childList")
   public ResponseData queryPageList(SysCategory sysCategory,HttpServletRequest req) {
       QueryWrapper<SysCategory> queryWrapper = QueryGenerator.initQueryWrapper(sysCategory, req.getParameterMap());
       List<SysCategory> list = sysCategoryService.list(queryWrapper);
       return ResponseData.success(list);
   }


   /**
     *   添加
    * @param sysCategory
    * @return
    */
   @PostMapping(value = "/add")
   public ResponseData add(@RequestBody SysCategory sysCategory) {
       try {
           sysCategoryService.addSysCategory(sysCategory);
          return ResponseData.success(ResponseData.DEFAULT_SUCCESS_CODE,"添加成功！",null);
       } catch (Exception e) {
           log.error(e.getMessage(),e);
           return ResponseData.error("操作失败");
       }
   }

   /**
     *  编辑
    * @param sysCategory
    * @return
    */
   @PutMapping(value = "/edit")
   public ResponseData edit(@RequestBody SysCategory sysCategory) {
       SysCategory sysCategoryEntity = sysCategoryService.getById(sysCategory.getId());
       if(sysCategoryEntity==null) {
         return  ResponseData.error("未找到对应实体");
       }else {
           sysCategoryService.updateSysCategory(sysCategory);
         return  ResponseData.success(ResponseData.DEFAULT_SUCCESS_CODE,"修改成功!",null);
       }
   }

   /**
     *   通过id删除
    * @param id
    * @return
    */
   @DeleteMapping(value = "/delete")
   public ResponseData delete(@RequestParam(name="id",required=true) String id) {
       SysCategory sysCategory = sysCategoryService.getById(id);
       if(sysCategory==null) {
          return ResponseData.error("未找到对应实体");
       }else {
           boolean ok = sysCategoryService.removeById(id);
           if(ok) {
               return ResponseData.success(ResponseData.DEFAULT_SUCCESS_CODE,"删除成功!",null);
           }
           return ResponseData.error("删除成功!");
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
          return  ResponseData.error("参数不识别！");
       }else {
           this.sysCategoryService.removeByIds(Arrays.asList(ids.split(",")));
           return ResponseData.success(ResponseData.DEFAULT_SUCCESS_CODE,"删除成功!",null);
       }
   }

   /**
     * 通过id查询
    * @param id
    * @return
    */
   @GetMapping(value = "/queryById")
   public ResponseData queryById(@RequestParam(name="id",required=true) String id) {
       SysCategory sysCategory = sysCategoryService.getById(id);
       if(sysCategory==null) {
           return ResponseData.error("未找到对应实体");
       }else {
           return ResponseData.success(sysCategory);
       }
   }

 /**
     * 导出excel
  *
  * @param request
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, SysCategory sysCategory) {
     // Step.1 组装查询条件查询数据
     QueryWrapper<SysCategory> queryWrapper = QueryGenerator.initQueryWrapper(sysCategory, request.getParameterMap());
     List<SysCategory> pageList = sysCategoryService.list(queryWrapper);
     // Step.2 AutoPoi 导出Excel
     ModelAndView mv = new ModelAndView();//new ModelAndView(new JeecgEntityExcelView());
     // 过滤选中数据
     String selections = request.getParameter("selections");
     if(oConvertUtils.isEmpty(selections)) {
         mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
     }else {
         List<String> selectionList = Arrays.asList(selections.split(","));
         List<SysCategory> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
         mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
     }
     //导出文件名称
     mv.addObject(NormalExcelConstants.FILE_NAME, "分类字典列表");
     mv.addObject(NormalExcelConstants.CLASS, SysCategory.class);
//     LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
     LoginUser user = new LoginUser();
     mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("分类字典列表数据", "导出人:"+user.getRealname(), "导出信息"));
     return mv;
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
     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
     Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
     for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
         MultipartFile file = entity.getValue();// 获取上传文件对象
         ImportParams params = new ImportParams();
         params.setTitleRows(2);
         params.setHeadRows(1);
         params.setNeedSave(true);
         try {
             List<SysCategory> listSysCategorys = ExcelImportUtil.importExcel(file.getInputStream(), SysCategory.class, params);
             for (SysCategory sysCategoryExcel : listSysCategorys) {
                 sysCategoryService.save(sysCategoryExcel);
             }
             return ResponseData.success("文件导入成功！数据行数：" + listSysCategorys.size());
         } catch (Exception e) {
             log.error(e.getMessage(), e);
             return ResponseData.error("文件导入失败："+e.getMessage());
         } finally {
             try {
                 file.getInputStream().close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     return ResponseData.error("文件导入失败！");
 }



 /**
    * 加载单个数据 用于回显
  */
   @RequestMapping(value = "/loadOne", method = RequestMethod.GET)
    public ResponseData loadOne(@RequestParam(name="field") String field, @RequestParam(name="val") String val) {
        ResponseData result = new ResponseData();
        try {

            QueryWrapper<SysCategory> query = new QueryWrapper<SysCategory>();
            query.eq(field, val);
            List<SysCategory> ls = this.sysCategoryService.list(query);
            if(ls==null || ls.size()==0) {
                result.setMessage("查询无果");
                 result.setSuccess(false);
            }else if(ls.size()>1) {
                result.setMessage("查询数据异常,["+field+"]存在多个值:"+val);
                 result.setSuccess(false);
            }else {
                return ResponseData.success(ls.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error(e.getMessage());
        }
       return ResponseData.error("查询失败!");
    }

   /**
         * 加载节点的子数据
    */
   @RequestMapping(value = "/loadTreeChildren", method = RequestMethod.GET)
   public ResponseData loadTreeChildren(@RequestParam(name="pid") String pid) {
       try {
           List<TreeSelectModel> ls = this.sysCategoryService.queryListByPid(pid);
           return ResponseData.success(ls);
       } catch (Exception e) {
           e.printStackTrace();
           return ResponseData.error(e.getMessage());
       }
   }

   /**
        * 加载一级节点/如果是同步 则所有数据
    */
   @RequestMapping(value = "/loadTreeRoot", method = RequestMethod.GET)
      public ResponseData loadTreeRoot(@RequestParam(name="async") Boolean async, @RequestParam(name="pcode") String pcode) {
          try {
              List<TreeSelectModel> ls = this.sysCategoryService.queryListByCode(pcode);
              if(!async) {
                  loadAllCategoryChildren(ls);
              }
              return ResponseData.success(ls);
          } catch (Exception e) {
              e.printStackTrace();
              return ResponseData.error(e.getMessage());
          }
      }

   /**
        * 递归求子节点 同步加载用到
    */
     private void loadAllCategoryChildren(List<TreeSelectModel> ls) {
         for (TreeSelectModel tsm : ls) {
           List<TreeSelectModel> temp = this.sysCategoryService.queryListByPid(tsm.getKey());
           if(temp!=null && temp.size()>0) {
               tsm.setChildren(temp);
               loadAllCategoryChildren(temp);
           }
       }
     }

    /**
     * 校验编码
     * @param pid
     * @param code
     * @return
     */
    @GetMapping(value = "/checkCode")
    public ResponseData checkCode(@RequestParam(name="pid",required = false) String pid, @RequestParam(name="code",required = false) String code) {
       if(oConvertUtils.isEmpty(code)){
           return ResponseData.error("错误,类型编码为空!");
       }
       if(oConvertUtils.isEmpty(pid)){
           return ResponseData.success();
       }
       SysCategory parent = this.sysCategoryService.getById(pid);
       if(code.startsWith(parent.getCode())){
           return ResponseData.success();
       }else{
           return ResponseData.error("编码不符合规范,须以\""+parent.getCode()+"\"开头!");
       }

    }


    /**
     * 分类字典树控件 加载节点
     * @param pid
     * @param pcode
     * @param condition
     * @return
     */
    @RequestMapping(value = "/loadTreeData", method = RequestMethod.GET)
    public ResponseData loadDict(@RequestParam(name="pid",required = false) String pid, @RequestParam(name="pcode",required = false) String pcode, @RequestParam(name="condition",required = false) String condition) {
        //pid如果传值了 就忽略pcode的作用
        if(oConvertUtils.isEmpty(pid)){
            if(oConvertUtils.isEmpty(pcode)){
               return ResponseData.error("加载分类字典树参数有误.[null]!");
           }else{
                if(sysCategoryService.ROOT_PID_VALUE.equals(pcode)){
                   pid = sysCategoryService.ROOT_PID_VALUE;
               }else{
                   pid = this.sysCategoryService.queryIdByCode(pcode);
               }
               if(oConvertUtils.isEmpty(pid)){
                   return ResponseData.error("加载分类字典树参数有误.[code]!");
               }
           }
        }
        Map<String, String> query = null;
        if(oConvertUtils.isNotEmpty(condition)) {
            query = JSON.parseObject(condition, Map.class);
        }
        List<TreeSelectModel> ls = sysCategoryService.queryListByPid(pid,query);
        return ResponseData.success(ls);
    }

    /**
     * 分类字典控件数据回显[表单页面]
     * @return
     */
    @RequestMapping(value = "/loadDictItem", method = RequestMethod.GET)
    public ResponseData loadDictItem(@RequestParam(name="ids") String ids) {
        LambdaQueryWrapper<SysCategory> query = new LambdaQueryWrapper<SysCategory>().in(SysCategory::getId,ids);
        List<SysCategory> list = this.sysCategoryService.list(query);
        List<String> textList = new ArrayList<String>();
        for (String id : ids.split(",")) {
            for (SysCategory c : list) {
               if(id.equals(c.getId())){
                   textList.add(c.getName());
                   break;
               }
            }
        }
        return ResponseData.success(textList);
    }


    /**
     * [列表页面]加载分类字典数据 用于值的替换
     * @param code
     * @return
     */
    @RequestMapping(value = "/loadAllData", method = RequestMethod.GET)
    public ResponseData loadAllData(@RequestParam(name="code",required = true) String code) {
        LambdaQueryWrapper<SysCategory> query = new LambdaQueryWrapper<SysCategory>();
        if(oConvertUtils.isNotEmpty(code) && !"0".equals(code)){
            query.likeRight(SysCategory::getCode,code);
        }
        List<SysCategory> list = this.sysCategoryService.list(query);
        if(list==null || list.size()==0) {
            return ResponseData.error("无数据,参数有误.[code]");
        }
        List<DictModel> rdList = new ArrayList<DictModel>();
        for (SysCategory c : list) {
            rdList.add(new DictModel(c.getId(),c.getName()));
        }
        return ResponseData.success(rdList);
    }

}

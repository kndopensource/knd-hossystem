package com.hos.coltransfer.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 数据采集上传管理控制层
 * @Author: zt
 * @CreateDate: 2020/4/7 12:17
 * @Version: 1.0
 */
@RestController
@RequestMapping("/coltransferController")
@Slf4j
public class ColtransferController {

    /**
     * 患者信息定时删除--deviceCode校验合法性
     * /api/collect/saveTaskInfo   任务信息保存(仅用于胶片报告上传)
     * /api/collect/taskInfoExist  获取任务信息(仅用于胶片报告上传 上传前判断任务信息是否存在)
     * /api/collect/uploadFilm     胶片上传接口
     * /api/collect/uploadReport   报告上传接口
     * /api/collect/delTask        报告胶片删除接口
     * /api/collect/uploadDicom    Dicom上传接口
     * swagger 注解 https://www.cnblogs.com/fengli9998/p/7921601.html
     * //    bus_file_info 文件上传信息表
     *
     */

//    /**
//     * @param file
//     * @param operatorNo
//     * @param operatorName
//     * @return
//     */
//    @ApiOperation(value="任务信息保存",tags={"任务信息保存"},notes="仅用于胶片报告上传")
//    @PostMapping("saveTaskInfo")
//    public ResponseData saveTaskInfo(@RequestBody @ApiParam(name="数据对象",value="传入json格式",required=true) Object obj) {
//        //1为已经上传过患者信息 0 为未上传过患者信息 上传报告胶片前需要先上传患者信息
//
//
//        return ResponseData.success();
//    }

//    /**
//     * 判断采集任务前置条件是否满足
//     * @param accno
//     * @param selfTaskCode
//     * @return ResponseData
//     */
//    @BussinessLog(value = "获取任务信息接口调用", key = "taskInfoExist", dict = RoleDict.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "accno", value = "检查号",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "selfTaskCode", value = "自助任务编码",required = true, dataType = "String")
//    })
//    @ApiOperation(value="获取任务信息",tags={"获取任务信息"},notes="仅用于胶片/报告上传,上传前判断任务信息是否存在,1为已经上传过患者信息 0 为未上传过患者信息 上传报告胶片前需要先上传患者信息")
//    @PostMapping("taskInfoExist")
//    public ResponseData taskInfoExist(String accno,String selfTaskCode) {
//
//        log.info("==========获取任务信息接口调用参数==========");
//        log.info("检查号:"+accno);
//        log.info("自助任务编码:"+selfTaskCode);
//
//
//        return ResponseData.success();
//
//    }
//
//
//    @BussinessLog(value = "胶片信息采集接口调用", key = "uploadFilm", dict = RoleDict.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "accno", value = "检查号",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "filePath", value = "文件路径(精确到文件名)",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "fileId", value = "文件id",required = true, dataType = "String")
//    })
//    @ApiOperation(value="uploadFilm",tags={"胶片信息采集接口"},notes="胶片文件信息上传到云端OSS存储")
//    @PostMapping("uploadFilm")
//    public ResponseData uploadFilm(@RequestParam("accno") String accno,
//                                   @RequestParam("filePath") String filePath,
//                                   @RequestParam("fileId") String fileId) {
//        log.info("==========胶片信息采集接口调用参数==========");
//        log.info("检查号:"+accno);
//        log.info("文件路径(精确到文件名):"+filePath);
//        log.info("文件id:"+fileId);
//
//        return ResponseData.success();
//
//    }
//
//    @BussinessLog(value = "报告信息采集接口调用", key = "uploadReport", dict = RoleDict.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "accno", value = "检查号",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "filePath", value = "文件路径(精确到文件名)",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "fileId", value = "文件id",required = true, dataType = "String")
//    })
//    @ApiOperation(value="uploadReport",tags={"报告信息采集接口"},notes="报告文件信息上传到云端OSS存储")
//    @PostMapping("uploadReport")
//    public ResponseData importExcel(@RequestParam("accno") String accno,
//                                    @RequestParam("filePath") String filePath,
//                                    @RequestParam("fileId") String fileId) {
//        log.info("==========报告信息采集接口调用参数==========");
//        log.info("检查号:"+accno);
//        log.info("文件路径(精确到文件名):"+filePath);
//        log.info("文件id:"+fileId);
//
//        return ResponseData.success();
//    }
//
//    @BussinessLog(value = "报告/胶片任务删除接口调用", key = "delTask", dict = RoleDict.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "accno", value = "检查号",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "fileId", value = "文件id",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "type", value = "1为报告,2为胶片",required = true, dataType = "String"),
//    })
//    @ApiOperation(value="delTask",tags={"报告/胶片任务删除接口"},notes="通知采集端删除云端任务")
//    @PostMapping("delTask")
//    public ResponseData delTask(@RequestParam("accno") String accno,
//                                @RequestParam("fileId") String fileId,
//                                @RequestParam("type") String type) {
//
//        log.info("检查号:"+accno);
//        log.info("文件id:"+fileId);
//        log.info("文件类型:"+type);
//
//        return ResponseData.success();
//
//    }
//    @ApiOperation(value="uploadDicom",tags={"Dicom信息采集接口"},notes="Dicom信息采集并上传到云端系统")
//    @PostMapping("uploadDicom")
//    public ResponseData uploadDicom(@RequestParam("file") MultipartFile file,
//                                @RequestParam("operatorNo") String operatorNo,
//                                @RequestParam("operatorNo") String operatorName) {
//
//
//        return ResponseData.success();
//
//    }

//    @ApiOperation(value="uploadFilm",tags={"任务信息保存"},notes="仅用于胶片报告上传")
//    @PostMapping("saveTaskInfo")
//    public ResponseData importExcel(@RequestParam("file") MultipartFile file,
//                                    @RequestParam("operatorNo") String operatorNo,
//                                    @RequestParam("operatorNo") String operatorName) {
//        return ResponseData.success();
//
//    }

}

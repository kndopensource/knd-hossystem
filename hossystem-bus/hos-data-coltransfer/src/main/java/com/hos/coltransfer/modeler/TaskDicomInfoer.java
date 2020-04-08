package com.hos.coltransfer.modeler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Description: java类作用描述
 * @Author: ZT
 * @CreateDate: 2020/4/7 16:43
 * @Version: 1.0
 */
@ApiModel(value="TaskInfoer对象",description="胶片/报告信息对象TaskInfoer")
@Data
public class TaskDicomInfoer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="患者拼音",name="patientName",example="zhangsan")
    private String patientSname;

    @ApiModelProperty(value="患者性别(0女 1男)",name="patientSex",example="0")
    private Integer patientSex;

    @ApiModelProperty(value="患者年龄",name="patientAge",example="10")
    private String patientAge;

    @ApiModelProperty(value="患者手机号码",name="patientTele",example="15856245411")
    private String patientTele;

    @ApiModelProperty(value="检查号",name="accno",example="125478",required=true)
    private String accno;

    @ApiModelProperty(value="文件路径(dicom文件所在目录)",name="filePath",example="",required=true)
    private String filePath;

    @ApiModelProperty(value="dicom解析信息",name="dicomJson",example="",required=true)
    private String dicomJson;

    @ApiModelProperty(value="dicom文件信息",name="fileJson",example="",required=true)
    private Integer fileJson;

    @ApiModelProperty(value="检查日期",name="checkDate",example="",required=true)
    private LocalDate checkDate;

}


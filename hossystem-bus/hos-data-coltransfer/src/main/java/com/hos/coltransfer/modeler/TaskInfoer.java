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
public class TaskInfoer implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value="id数组",hidden=true)
//    private String[] ids;
//    private List<String> idList;

    @ApiModelProperty(value="任务编码",name="taskCode",example="1254554dfd",required=true)
    private String taskCode;

    @ApiModelProperty(value="患者名称",name="patientName",example="张三",required=true)
    private String patientName;

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

    @ApiModelProperty(value="检查类型",name="checkType",example="")
    private String checkType;

    @ApiModelProperty(value="检查部位",name="checkPart",example="")
    private String checkPart;

    @ApiModelProperty(value="是否住院",name="isHospitalization",example="")
    private Integer isHospitalization;

    @ApiModelProperty(value="自助任务编码",name="selfTaskCode",example="d2121fdf",required=true)
    private String selfTaskCode;

    @ApiModelProperty(value="检查日期",name="checkDate",example="",required=true)
    private LocalDate checkDate;

}


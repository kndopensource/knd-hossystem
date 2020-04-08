package com.hos.coltransfer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 任务信息明显表
 * </p>
 *
 * @author zt
 * @since 2020-04-07
 */
@TableName("bus_task_info")
@Data
public class TaskInfo implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键编码
     */
        @TableId(value = "_id", type = IdType.AUTO)
      private Integer id;

      /**
     * 任务编码
     */
      private String taskCode;

      /**
     * 患者名称
     */
      private String patientName;

      /**
     * 患者拼音
     */
      private String patientSname;

      /**
     * 患者性别(0女 1男)
     */
      private Integer patientSex;

      /**
     * 患者年龄
     */
      private String patientAge;

      /**
     * 患者手机号码
     */
      private String patientTele;

      /**
     * 检查号
     */
      private String accno;

      /**
     * 检查类型
     */
      private String checkType;

      /**
     * 检查部位
     */
      private String checkPart;

      /**
     * 是否住院
     */
      private Integer isHospitalization;

      /**
     * 自助任务编码
     */
      private String selfTaskCode;

      /**
     * 检查日期
     */
      private LocalDate checkDate;

      /**
     * 创建时间
     */
      private LocalDateTime createDate;

      /**
     * 修改时间
     */
      private LocalDateTime updateDate;

}

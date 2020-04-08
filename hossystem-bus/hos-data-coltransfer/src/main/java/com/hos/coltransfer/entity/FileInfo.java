package com.hos.coltransfer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 需要上传的文件信息表，文件提供文件路径  文件类型
 * </p>
 *
 * @author zt
 * @since 2020-04-07
 */
@TableName("bus_file_info")
@Data
public class FileInfo implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键编码
     */
        private String id;

      /**
     * 任务编码
     */
      private String taskCode;

      /**
     * 检查号
     */
      private String accno;

      /**
     * 文件类型
     */
      private Boolean fileType;

      /**
     * 文件路径
     */
      private String filePath;

      /**
     * dicom解析信息
     */
      private String dicomJson;

      /**
     * dicom文件信息
     */
      private String fileJson;

      /**
     * 同步状态
     */
      private Boolean synchState;

      /**
     * 同步次数
     */
      private Integer synchNum;

      /**
     * 文件id(当为胶片报告的时候需要)
     */
      private String fileId;

}

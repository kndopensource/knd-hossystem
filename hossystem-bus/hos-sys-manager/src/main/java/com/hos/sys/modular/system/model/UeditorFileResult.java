package com.hos.sys.modular.system.model;

import lombok.Data;

/**
 * ueditor文件上传返回结果
 *
 * @author dev
 * @Date 2017/5/5 22:40
 */
@Data
public class UeditorFileResult {

    /**
     * 状态：固定值SUCCESS
     */
    private String state = "SUCCESS";

    /**
     * 图片相对路径
     */
    private String url;

    /**
     * 文件名称（这里我使用随机字符串来重新命名图片）
     */
    private String title;

    /**
     * 文件名称（这里我使用随机字符串来重新命名图片）
     */
    private String original;
}

package com.hos.base.enums;

import lombok.Getter;

/**
 * 公共状态
 *
 * @author hossystem
 * @Date 2017年1月22日 下午12:14:59
 */
@Getter
public enum CommonStatus {

    ENABLE("ENABLE", "启用"),
    DISABLE("DISABLE", "禁用");

    String code;
    String message;

    CommonStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getDescription(String status) {
        if (status == null) {
            return "";
        } else {
            for (CommonStatus s : CommonStatus.values()) {
                if (s.getCode().equals(status)) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}

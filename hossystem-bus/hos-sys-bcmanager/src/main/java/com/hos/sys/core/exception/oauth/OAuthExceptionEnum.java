package com.hos.sys.core.exception.oauth;

import cn.stylefeng.roses.kernel.model.exception.AbstractBaseExceptionEnum;

/**
 * 第三方登录异常枚举
 *
 * @author 众神
 * @Date 2019/6/9 18:45
 */
public enum OAuthExceptionEnum implements AbstractBaseExceptionEnum {

    OPEN_ID_ALREADY_BIND(500, "当前openId已有人绑定！"),
    OAUTH_RESPONSE_ERROR(400, "oauth server错误");

    OAuthExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

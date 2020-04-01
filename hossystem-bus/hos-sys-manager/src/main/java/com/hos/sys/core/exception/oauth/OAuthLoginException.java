package com.hos.sys.core.exception.oauth;

import cn.stylefeng.roses.kernel.model.exception.AbstractBaseExceptionEnum;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

/**
 * 第三方登录异常
 *
 * @author 众神
 * @Date 2019/6/9 18:43
 */
public class OAuthLoginException extends ServiceException {

    public OAuthLoginException(AbstractBaseExceptionEnum exception) {
        super(exception);
    }

}

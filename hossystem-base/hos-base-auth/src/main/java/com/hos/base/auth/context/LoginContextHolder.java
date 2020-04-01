package com.hos.base.auth.context;

import cn.stylefeng.roses.core.util.SpringContextHolder;

/**
 * 当前登录用户信息获取的接口
 *
 * @author 众神
 * @Date 2019/7/18 22:27
 */
public class LoginContextHolder {

    public static LoginContext getContext() {
        return SpringContextHolder.getBean(LoginContext.class);
    }

}

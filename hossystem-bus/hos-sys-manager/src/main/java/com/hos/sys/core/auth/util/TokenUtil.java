package com.hos.sys.core.auth.util;

import cn.stylefeng.roses.core.util.HttpContext;
import cn.stylefeng.roses.core.util.ToolUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static com.hos.base.consts.ConstantsContext.getTokenHeaderName;

/**
 * 获取token的封装
 *
 * @author 众神
 * @Date 2020/2/16 22:51
 */
public class TokenUtil {

    /**
     * 获取token的两种方法
     *
     * @author 众神
     * @Date 2020/2/16 22:51
     */
    public static String getToken() {

        String authToken = null;
        HttpServletRequest request = HttpContext.getRequest();

        //权限校验的头部
        String tokenHeader = getTokenHeaderName();
        authToken = request.getHeader(tokenHeader);

        //header中没有的话去cookie拿值，以header为准
        if (ToolUtil.isEmpty(authToken)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (tokenHeader.equals(cookie.getName())) {
                        authToken = cookie.getValue();
                    }
                }
            }
        }

        return authToken;
    }
}

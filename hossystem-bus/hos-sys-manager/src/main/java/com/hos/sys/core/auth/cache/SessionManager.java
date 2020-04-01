package com.hos.sys.core.auth.cache;

import com.hos.base.auth.model.LoginUser;

/**
 * 会话管理
 *
 * @author 众神
 * @date 2019-09-28-14:43
 */
public interface SessionManager {

    /**
     * 缓存前缀
     */
    String SESSION_PREFIX = "LOGIN_USER_";

    /**
     * 创建会话
     *
     * @author 众神
     * @Date 2019-09-28 14:50
     */
    void createSession(String token, LoginUser loginUser);

    /**
     * 获取会话
     *
     * @author 众神
     * @Date 2019-09-28 14:50
     */
    LoginUser getSession(String token);

    /**
     * 删除会话
     *
     * @author 众神
     * @Date 2019-09-28 14:50
     */
    void removeSession(String token);

    /**
     * 是否已经登陆
     *
     * @author 众神
     * @Date 2019-09-28 14:56
     */
    boolean haveSession(String token);

}

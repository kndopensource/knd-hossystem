package com.hos.sys.modular.system.controller;

import com.hos.base.auth.context.LoginContextHolder;
import com.hos.base.auth.exception.AuthException;
import com.hos.base.auth.exception.enums.AuthExceptionEnum;
import com.hos.base.auth.jwt.JwtTokenUtil;
import com.hos.base.auth.jwt.payload.JwtPayLoad;
import com.hos.base.auth.model.LoginUser;
import com.hos.sys.core.util.DefaultImages;
import com.hos.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.hos.base.consts.ConstantsContext.getJwtSecretExpireSec;
import static com.hos.base.consts.ConstantsContext.getTokenHeaderName;

/**
 * 单点登录控制器
 *
 * @author 众神
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class SsoLoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 单点登录入口
     *
     * @author 众神
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/ssoLogin", method = RequestMethod.GET)
    public String ssoLogin(@RequestParam("token") String token, HttpServletResponse response, Model model) {

        //登录并创建token
        Claims claimFromToken = JwtTokenUtil.getClaimFromToken(token);
        System.out.println(claimFromToken);
        Long accountId = (Long) claimFromToken.get("accountId");
        System.out.println(accountId);

        //记录登录日志
        //LogManager.me().executeLog(LogTaskFactory.loginLog(Long.valueOf(accountId), getIp()));

        String token1 = JwtTokenUtil.generateToken(new JwtPayLoad(1L, "admin", "1"));

        //创建token
        Cookie authorization = new Cookie(getTokenHeaderName(), token1);
        authorization.setMaxAge(getJwtSecretExpireSec().intValue());
        authorization.setHttpOnly(true);
        response.addCookie(authorization);

        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername("admin");
        } catch (UsernameNotFoundException e) {
            throw new AuthException(AuthExceptionEnum.LOGIN_EXPPIRED);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //获取当前用户角色列表
        LoginUser user = LoginContextHolder.getContext().getUser();
        List<Long> roleList = user.getRoleList();

        if (roleList == null || roleList.size() == 0) {
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }

        List<Map<String, Object>> menus = userService.getUserMenuNodes(roleList);
        model.addAttribute("menus", menus);
        model.addAttribute("avatar", DefaultImages.defaultAvatarUrl());
        model.addAttribute("name", user.getName());

        return "/index.html";
    }

    /**
     * 单点登录退出
     *
     * @author 众神
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/ssoLogout")
    public String ssoLogout(@RequestParam("token") String token, HttpServletResponse response, Model model) {
        return null;
    }

}
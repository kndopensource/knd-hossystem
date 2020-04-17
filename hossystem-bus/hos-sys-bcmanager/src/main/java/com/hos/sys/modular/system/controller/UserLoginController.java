package com.hos.sys.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.mutidatasource.DataSourceContextHolder;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.hos.base.auth.service.AuthService;
import com.hos.base.consts.ConstantsContext;
import com.hos.base.tenant.context.DataBaseNameHolder;
import com.hos.base.tenant.context.TenantCodeHolder;
import com.hos.base.tenant.entity.TenantInfo;
import com.hos.base.tenant.service.TenantInfoService;
import com.hos.sys.core.auth.cache.SessionManager;
import com.hos.sys.core.exception.InvalidKaptchaException;
import com.hos.sys.core.exception.enums.BizExceptionEnum;
import com.hos.sys.modular.system.entity.SysDepart;
import com.hos.sys.modular.system.entity.SysUser;
import com.hos.sys.modular.system.modeler.SysLoginModel;
import com.hos.sys.modular.system.service.ISysDepartService;
import com.hos.sys.modular.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * zt
 */
@RestController
@RequestMapping("/sys")
@Api(tags="用户登录")
@Slf4j
public class UserLoginController extends BaseController {

	@Autowired
	private AuthService authService;

	@Autowired
	private UserService userService;

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private ISysDepartService sysDepartService;

	@Autowired
	private ISysUserService sysUserService;

	
	private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

	@ApiOperation("登录接口")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseData login(@RequestBody SysLoginModel sysLoginModel){

		String username = sysLoginModel.getUsername();
		String password = sysLoginModel.getPassword();


		if (ToolUtil.isOneEmpty(username, password)) {
			throw new RequestEmptyException("账号或密码为空！");
		}

		//如果系统开启了多租户开关，则添加租户的临时缓存
		if (ConstantsContext.getTenantOpen()) {
			String tenantCode = super.getPara("tenantCode");
			if (ToolUtil.isNotEmpty(tenantCode)) {

				//从spring容器中获取service，如果没开多租户功能，没引入相关包，这里会报错
				TenantInfoService tenantInfoService = null;
				try {
					tenantInfoService = SpringContextHolder.getBean(TenantInfoService.class);
				} catch (Exception e) {
					throw new ServiceException(500, "找不到多租户service，请检查是否引入guns-tenant模块！");
				}

				//获取租户信息
				TenantInfo tenantInfo = tenantInfoService.getByCode(tenantCode);
				if (tenantInfo != null) {
					String dbName = tenantInfo.getDbName();

					//添加临时变量（注意销毁）
					TenantCodeHolder.put(tenantCode);
					DataBaseNameHolder.put(dbName);
					DataSourceContextHolder.setDataSourceType(dbName);
				} else {
					throw new ServiceException(BizExceptionEnum.NO_TENANT_ERROR);
				}
			}
		}

		//验证验证码是否正确
		if (ConstantsContext.getKaptchaOpen()) {
			String kaptcha = super.getPara("kaptcha").trim();
			String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
				throw new InvalidKaptchaException();
			}
		}

		//登录并创建token
		String token = authService.login(username, password);

		SysUser sysUser = sysUserService.getUserByName(username);

		// 获取用户部门信息
		JSONObject obj = new JSONObject();
		List<SysDepart> departs = sysDepartService.queryUserDeparts(sysUser.getId());
		obj.put("departs", departs);
		if (departs == null || departs.size() == 0) {
			obj.put("multi_depart", 0);
		} else if (departs.size() == 1) {
			sysUserService.updateUserDepart(username, departs.get(0).getOrgCode());
			obj.put("multi_depart", 1);
		} else {
			obj.put("multi_depart", 2);
		}
		obj.put("token", token);
		obj.put("userInfo", sysUser);

	    return ResponseData.success(token);
	}
	
//	/**
//	 * 退出登录
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/logout")
//	public ResponseData logout(HttpServletRequest request, HttpServletResponse response) {
//		//用户退出逻辑
//		return ResponseData.success();
//	}
//
//	/**
//	 * 获取访问量
//	 * @return
//	 */
//	@GetMapping("loginfo")
//	public ResponseData loginfo() {
//
//		return ResponseData.success();
//	}
//
//	/**
//	 * 获取访问量
//	 * @return
//	 */
//	@GetMapping("visitInfo")
//	public Result<List<Map<String,Object>>> visitInfo() {
//		return ResponseData.success();
//	}
//
//
//	/**
//	 * 登陆成功选择用户当前部门
//	 * @param user
//	 * @return
//	 */
//	@RequestMapping(value = "/selectDepart", method = RequestMethod.PUT)
//	public ResponseData selectDepart(@RequestBody SysUser user) {
//		return ResponseData.success();
//	}
//
//	/**
//	 * 短信登录接口
//	 *
//	 * @param jsonObject
//	 * @return
//	 */
//	@PostMapping(value = "/sms")
//	public Result<String> sms(@RequestBody JSONObject jsonObject) {
//		return ResponseData.success();
//	}
//
//
//	/**
//	 * 手机号登录接口
//	 *
//	 * @param jsonObject
//	 * @return
//	 */
//	@ApiOperation("手机号登录接口")
//	@PostMapping("/phoneLogin")
//	public ResponseData phoneLogin(@RequestBody JSONObject jsonObject) {
//		return ResponseData.success();
//	}
//
//
//	/**
//	 * 用户信息
//	 *
//	 * @param sysUser
//	 * @param result
//	 * @return
//	 */
//	private ResponseData userInfo(SysUser sysUser, ResponseData result) {
//
//		return ResponseData.success();
//	}
//
//	/**
//	 * 获取加密字符串
//	 * @return
//	 */
//	@GetMapping(value = "/getEncryptedString")
//	public Result<Map<String,String>> getEncryptedString(){
//		return ResponseData.success();
//	}
//
//	/**
//	 * 获取校验码
//	 */
//	@ApiOperation("获取验证码")
//	@GetMapping(value = "/getCheckCode")
//	public Result<Map<String,String>> getCheckCode(){
//		return ResponseData.success();
//	}
//
//	/**
//	 * 后台生成图形验证码
//	 * @param response
//	 * @param key
//	 */
//	@ApiOperation("获取验证码2")
//	@GetMapping(value = "/randomImage/{key}")
//	public Result<String> randomImage(HttpServletResponse response, @PathVariable String key){
//		return ResponseData.success();
//	}
//
//	/**
//	 * app登录
//	 * @param sysLoginModel
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mLogin", method = RequestMethod.POST)
//	public ResponseData mLogin(@RequestBody SysLoginModel sysLoginModel) throws Exception {
//		return ResponseData.success();
//	}

}
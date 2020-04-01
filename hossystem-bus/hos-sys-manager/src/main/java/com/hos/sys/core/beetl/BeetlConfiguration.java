package com.hos.sys.core.beetl;

import com.hos.base.auth.context.LoginContext;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.hos.base.consts.ConstantsContext;
import com.hos.base.i18n.context.UserTranslationContext;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

/**
 * beetl拓展配置,绑定一些工具类,方便在模板中直接调用
 *
 * @author dev
 * @Date 2018/2/22 21:03
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    private LoginContext loginContext;

    public BeetlConfiguration(LoginContext loginContext) {
        this.loginContext = loginContext;
    }

    @Override
    public void initOther() {
        groupTemplate.registerFunctionPackage("shiro", loginContext);
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("constants", new ConstantsContext());
        groupTemplate.registerFunctionPackage("lang", new UserTranslationContext());
    }
}

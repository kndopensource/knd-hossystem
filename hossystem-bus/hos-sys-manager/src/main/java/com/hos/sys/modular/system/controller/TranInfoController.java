package com.hos.sys.modular.system.controller;

import com.hos.base.i18n.context.TranslationContext;
import com.hos.base.i18n.context.UserTranslationContext;
import com.hos.base.i18n.enums.TranslationEnum;
import com.hos.base.i18n.enums.TranslationItem;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * 多语言表控制器
 *
 * @author dev
 * @Date 2019-10-17 22:20:54
 */
@Controller
@RequestMapping("/translation")
public class TranInfoController extends BaseController {

    /**
     * 获取当前用户字典
     *
     * @author dev
     * @Date 2019-06-20
     */
    @ResponseBody
    @RequestMapping("/getUserTranslation")
    public ResponseData getUserTranslation() {
        TranslationEnum userCurrentTrans = UserTranslationContext.getUserCurrentTrans();
        Map<String, String> translationByLanguage = TranslationContext.getTranslationByLanguage(userCurrentTrans);
        return ResponseData.success(translationByLanguage);
    }

    /**
     * 切换用户的语言
     *
     * @author dev
     * @Date 2019-06-20
     */
    @ResponseBody
    @RequestMapping("/changeUserTranslation")
    public ResponseData changeUserTranslation(@RequestParam("code") Integer code) {
        TranslationEnum translationEnum = TranslationEnum.valueOf(code);
        if (translationEnum == null) {
            throw new RequestEmptyException("请求不合法！code值错误！");
        }
        UserTranslationContext.setUserCurrentTrans(translationEnum);
        return SUCCESS_TIP;
    }

    /**
     * 获取当前支持的语言列表
     *
     * @author dev
     * @Date 2019-06-20
     */
    @ResponseBody
    @RequestMapping("/languages")
    public ResponseData languages() {
        TranslationEnum[] values = TranslationEnum.values();

        ArrayList<TranslationItem> results = new ArrayList<>();
        for (TranslationEnum value : values) {
            results.add(new TranslationItem(value.getCode(), value.getDescription()));
        }

        return new SuccessResponseData(results);
    }

}



package com.hos.admin.modular.demos.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * pdf预览
 *
 * @author 众神
 * @Date 2017年2月17日20:27:22
 */
@Controller
@Slf4j
public class PdfPreviewController extends BaseController {

    private String PREFIX = "/demos/pdf/";

    /**
     * pdf预览
     */
    @RequestMapping("/pdf")
    public String pdf() {
        return PREFIX + "pdf.html";
    }

    /**
     * 预览
     */
    @GetMapping(value = "/loadPdfFile", produces = "application/pdf")
    @ResponseBody
    public ClassPathResource loadPdfFile(@RequestParam(value = "file", required = false) String file) {

        if (ToolUtil.isEmpty(file)) {
            file = "demo.pdf";
        }

        try {
            return new ClassPathResource("assets/expand/pdf/demo/" + file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

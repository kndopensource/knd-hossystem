package com.hos.sys.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hos.base.auth.context.LoginContextHolder;
import com.hos.base.auth.model.LoginUser;
import com.hos.base.oshi.SystemHardwareInfo;
import com.hos.sys.core.constant.factory.ConstantFactory;
import com.hos.sys.core.log.LogObjectHolder;
import com.hos.sys.core.util.DefaultImages;
import com.hos.sys.modular.system.entity.Notice;
import com.hos.sys.modular.system.entity.User;
import com.hos.sys.modular.system.model.UploadResult;
import com.hos.sys.modular.system.service.FileInfoService;
import com.hos.sys.modular.system.service.NoticeService;
import com.hos.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

/**
 * 通用控制器
 *
 * @author 众神
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/system")
@Slf4j
public class SystemController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private NoticeService noticeService;

    /**
     * 控制台页面
     *
     * @author 众神
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/console")
    public String console() {
        return "/modular/frame/console.html";
    }

    /**
     * 分析页面
     *
     * @author 众神
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/console2")
    public String console2() {
        return "/modular/frame/console2.html";
    }

    /**
     * 系统硬件信息页面
     *
     * @author 众神
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/systemInfo")
    public String systemInfo(Model model) {

        SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
        systemHardwareInfo.copyTo();

        model.addAttribute("server", systemHardwareInfo);

        return "/modular/frame/systemInfo.html";
    }

    /**
     * 跳转到首页通知
     *
     * @author 众神
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/notice")
    public String hello() {
        List<Notice> notices = noticeService.list();
        super.setAttr("noticeList", notices);
        return "/modular/frame/notice.html";
    }

    /**
     * 主页面
     *
     * @author 众神
     * @Date 2019/1/24 3:38 PM
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "/modular/frame/welcome.html";
    }

    /**
     * 主题页面
     *
     * @author 众神
     * @Date 2019/1/24 3:38 PM
     */
    @RequestMapping("/theme")
    public String theme() {
        return "/modular/frame/theme.html";
    }

    /**
     * 锁屏界面
     *
     * @author 众神
     * @Date 2020/3/8 17:19
     */
    @RequestMapping("/lock")
    public String lock() {
        return "/modular/frame/lock-screen.html";
    }

    /**
     * 跳转到修改密码界面
     *
     * @author 众神
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return "/modular/frame/password.html";
    }

    /**
     * 个人消息列表
     *
     * @author 众神
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/message")
    public String message() {
        return "/modular/frame/message.html";
    }

    /**
     * 跳转到查看用户详情页面
     *
     * @author 众神
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        Long userId = LoginContextHolder.getContext().getUserId();
        User user = this.userService.getById(userId);

        model.addAllAttributes(BeanUtil.beanToMap(user));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
        model.addAttribute("avatar", DefaultImages.defaultAvatarUrl());
        LogObjectHolder.me().set(user);

        return "/modular/frame/user_info.html";
    }

    /**
     * 通用的树列表选择器
     *
     * @author 众神
     * @Date 2018/12/23 6:59 PM
     */
    @RequestMapping("/commonTree")
    public String deptTreeList(@RequestParam("formName") String formName,
                               @RequestParam("formId") String formId,
                               @RequestParam("treeUrl") String treeUrl, Model model) {

        if (ToolUtil.isOneEmpty(formName, formId, treeUrl)) {
            throw new RequestEmptyException("请求数据不完整！");
        }

        try {
            model.addAttribute("formName", URLDecoder.decode(formName, "UTF-8"));
            model.addAttribute("formId", URLDecoder.decode(formId, "UTF-8"));
            model.addAttribute("treeUrl", URLDecoder.decode(treeUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RequestEmptyException("请求数据不完整！");
        }

        return "/common/tree_dlg.html";
    }

    /**
     * 更新头像
     *
     * @author 众神
     * @Date 2018/11/9 12:45 PM
     */
    @RequestMapping("/updateAvatar")
    @ResponseBody
    public Object uploadAvatar(@RequestParam("fileId") String fileId) {

        if (ToolUtil.isEmpty(fileId)) {
            throw new RequestEmptyException("请求头像为空");
        }

        fileInfoService.updateAvatar(fileId);

        return SUCCESS_TIP;
    }

    /**
     * 预览头像
     *
     * @author 众神
     * @Date 2018/11/9 12:45 PM
     */
    @RequestMapping("/previewAvatar")
    @ResponseBody
    public Object previewAvatar(HttpServletResponse response) {

        //输出图片的文件流
        try {
            response.setContentType("image/jpeg");
            byte[] decode = this.fileInfoService.previewAvatar();
            response.getOutputStream().write(decode);
        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
        }

        return null;
    }

    /**
     * 获取当前用户详情
     *
     * @author 众神
     * @Date 2018/12/23 6:59 PM
     */
    @RequestMapping("/currentUserInfo")
    @ResponseBody
    public ResponseData getUserInfo() {

        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        return new SuccessResponseData(userService.getUserInfo(currentUser.getId()));
    }

    /**
     * layui上传组件 通用文件上传接口
     *
     * @author 众神
     * @Date 2019-2-23 10:48:29
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public ResponseData layuiUpload(@RequestPart("file") MultipartFile file) {

        UploadResult uploadResult = this.fileInfoService.uploadFile(file);
        String fileId = uploadResult.getFileId();

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", fileId);

        return ResponseData.success(0, "上传成功", map);
    }


}

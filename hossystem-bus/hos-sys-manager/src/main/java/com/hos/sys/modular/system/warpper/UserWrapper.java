package com.hos.sys.modular.system.warpper;

import com.hos.sys.core.constant.factory.ConstantFactory;
import com.hos.sys.core.util.DecimalUtil;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author 众神
 * @date 2017年2月13日 下午10:47:03
 */
public class UserWrapper extends BaseControllerWrapper {

    public UserWrapper(Map<String, Object> single) {
        super(single);
    }

    public UserWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public UserWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public UserWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("sexName", ConstantFactory.me().getSexName((String) map.get("sex")));
        map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleId")));
        map.put("deptName", ConstantFactory.me().getDeptName(DecimalUtil.getLong(map.get("deptId"))));
        map.put("statusName", ConstantFactory.me().getStatusName((String) map.get("status")));
        map.put("positionName", ConstantFactory.me().getPositionName(DecimalUtil.getLong(map.get("userId"))));
    }

}

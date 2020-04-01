package com.hos.sys.modular.system.warpper;

import com.hos.sys.core.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.enums.YesOrNotEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author 众神
 * @date 2017年2月19日15:07:29
 */
public class MenuWrapper extends BaseControllerWrapper {

    public MenuWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public MenuWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getMenuStatusName((String) map.get("status")));

        String menuFlag = (String) map.get("menuFlag");
        for (YesOrNotEnum value : YesOrNotEnum.values()) {
            if (value.name().equals(menuFlag)) {
                map.put("isMenuName", value.getDesc());
            }
        }

        //删除虚拟的父节点
        if (map.get("pcode").equals("0")) {
            map.remove("pcode");
        }

    }

}

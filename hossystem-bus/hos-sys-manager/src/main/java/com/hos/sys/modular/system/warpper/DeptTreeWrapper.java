package com.hos.sys.modular.system.warpper;

import com.hos.base.pojo.node.TreeviewNode;

import java.util.List;

/**
 * 部门列表树的包装
 *
 * @author 众神
 * @date 2017年4月25日 18:10:31
 */
public class DeptTreeWrapper {

    public static void clearNull(List<TreeviewNode> list) {
        if (list == null) {
            return;
        } else {
            if (list.size() == 0) {
                return;
            } else {
                for (TreeviewNode node : list) {
                    if (node.getNodes() != null) {
                        if (node.getNodes().size() == 0) {
                            node.setNodes(null);
                        } else {
                            clearNull(node.getNodes());
                        }
                    }
                }
            }
        }
    }
}

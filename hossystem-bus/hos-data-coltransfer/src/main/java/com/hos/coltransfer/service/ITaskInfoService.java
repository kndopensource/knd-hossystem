package com.hos.coltransfer.service;

import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.hos.coltransfer.entity.TaskInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务信息明显表 服务类
 * </p>
 *
 * @author zt
 * @since 2020-04-07
 */
public interface ITaskInfoService extends IService<TaskInfo> {
    /**
     * 获取任务信息
     */
    public ResponseData taskInfoExist(String accno,String selfTaskCode);

}

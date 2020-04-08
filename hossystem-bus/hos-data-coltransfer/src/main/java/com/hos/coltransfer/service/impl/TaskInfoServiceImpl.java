package com.hos.coltransfer.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hos.coltransfer.entity.TaskInfo;
import com.hos.coltransfer.mapper.TaskInfoMapper;
import com.hos.coltransfer.service.ITaskInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务信息明细表 服务实现类
 * </p>
 *
 * @author zt
 * @since 2020-04-07
 */
@Service
public class TaskInfoServiceImpl extends ServiceImpl<TaskInfoMapper, TaskInfo> implements ITaskInfoService {

}

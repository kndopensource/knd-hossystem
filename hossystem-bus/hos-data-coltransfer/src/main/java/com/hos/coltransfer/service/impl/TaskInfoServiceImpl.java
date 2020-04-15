package com.hos.coltransfer.service.impl;


import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hos.coltransfer.entity.TaskInfo;
import com.hos.coltransfer.mapper.TaskInfoMapper;
import com.hos.coltransfer.service.ITaskInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ResponseData taskInfoExist(String accno,String selfTaskCode){

        QueryWrapper<TaskInfo> queryWrapper = new QueryWrapper<TaskInfo>();
        queryWrapper.eq("accno",accno).eq("selfTaskCode",selfTaskCode);
        List<TaskInfo> userInfoList = this.baseMapper.selectList(queryWrapper);

        // 已经维护患者信息
        if(userInfoList!=null&&userInfoList.size()>0){
            return   ResponseData.success(1,"已经上传过患者信息","1");
        }
        // 未维护患者信息
        if(userInfoList==null||userInfoList.size()==0){
            return   ResponseData.success(1,"未上传过患者信息,上传胶片/报告前需要先上传患者信息","0");
        }
        return   ResponseData.success();
    }


}

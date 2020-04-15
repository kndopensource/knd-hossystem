package com.hos.coltransfer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hos.coltransfer.entity.FileInfo;
import com.hos.coltransfer.mapper.ColFileInfoMapper;
import com.hos.coltransfer.service.IFileInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 需要上传的文件信息表，文件提供文件路径  文件类型 服务实现类
 * </p>
 *
 * @author zt
 * @since 2020-04-07
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<ColFileInfoMapper, FileInfo> implements IFileInfoService {



}

package com.hos.coltransfer;

import com.hos.base.storage.aliyun.AliyunOssUtil;
import com.hos.base.storage.config.oss.OssClientFactoryBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;

/**
 * @Description: 上传文件测试
 * @Author: zt
 * @CreateDate: 2020/4/7 11:26
 * @Version: 1.0
 */
public class OssUploadTest {





    public static void main(String[] args) {

        AliyunOssUtil aliyunOssUtil = new AliyunOssUtil();
        try{
//            InputStream content = new FileInputStream(new File("G://workdocument/医疗系统架构.jpg"));
//            aliyunOssUtil.uploadFile2OSS(content,"医疗系统架构.jpg");
            System.out.println(LocalDate.now());
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}

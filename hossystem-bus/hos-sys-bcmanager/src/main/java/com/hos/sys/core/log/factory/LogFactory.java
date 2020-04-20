//package com.hos.sys.core.log.factory;
//
//import com.hos.sys.core.constant.state.LogSucceed;
//import com.hos.sys.core.constant.state.LogType;
//
//import java.util.Date;
//
///**
// * 日志对象创建工厂
// *
// * @author 众神
// * @date 2016年12月6日 下午9:18:27
// */
//public class LogFactory {
//
//    /**
//     * 创建操作日志
//     */
//    public static OperationLog createOperationLog(LogType logType, Long userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
//        OperationLog operationLog = new OperationLog();
//        operationLog.setLogType(logType.getMessage());
//        operationLog.setLogName(bussinessName);
//        operationLog.setUserId(userId);
//        operationLog.setClassName(clazzName);
//        operationLog.setMethod(methodName);
//        operationLog.setCreateTime(new Date());
//        operationLog.setSucceed(succeed.getMessage());
//        operationLog.setMessage(msg);
//        return operationLog;
//    }
//
//    /**
//     * 创建登录日志
//     */
//    public static LoginLog createLoginLog(LogType logType, Long userId, String msg, String ip) {
//        LoginLog loginLog = new LoginLog();
//        loginLog.setLogName(logType.getMessage());
//        loginLog.setUserId(userId);
//        loginLog.setCreateTime(new Date());
//        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
//        loginLog.setIpAddress(ip);
//        loginLog.setMessage(msg);
//        return loginLog;
//    }
//    public static LoginLog createLoginLog(LogType logType, String userId, String msg, String ip) {
//        LoginLog loginLog = new LoginLog();
//        loginLog.setLogName(logType.getMessage());
////        loginLog.setUserId(userId);  TODO
//        loginLog.setCreateTime(new Date());
//        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
//        loginLog.setIpAddress(ip);
//        loginLog.setMessage(msg);
//        return loginLog;
//    }
//}

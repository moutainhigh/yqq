//package com.yqq.third.utils;
//
//import com.yqqh.xwecp.service.BaseServiceInvocationResult;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @Desc ECP相关工具类
// * @Author zhangwei
// * @Date: 2019-09-17
// */
//public class ECPUtils {
//    //逻辑接口成功编码
//    public static final String LOGIC_SUCCESS = "0";
//
//    //逻辑接口失败编码
//    public static final String LOGIC_FAILURE = "1";
//
//    //逻辑接口返回提示
//    public static final String LOGIC_INFO = "2";
//    //渠道名称
//    public static final String CHANNEL_NAME = "equityCenter_channel";
//
//    /**
//     * ECP接口调用是否成功判断
//     *
//     * @param result
//     * @return
//     */
//    public static boolean isSuccess(BaseServiceInvocationResult result) {
//        if (result != null) {
//            if (LOGIC_SUCCESS.equals(result.getResultCode()) || LOGIC_INFO.equals(result.getResultCode())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    /**
//     * @desc 获取初始化ECP实例名
//     */
//    public static String getEcpInstanceName() {
//        return CHANNEL_NAME;
//    }
//
//    /**
//     * @return
//     * @desc 获取初始化ECP实例名
//     */
//    public static String getEcpChannleName() {
//        return CHANNEL_NAME;
//    }
//}

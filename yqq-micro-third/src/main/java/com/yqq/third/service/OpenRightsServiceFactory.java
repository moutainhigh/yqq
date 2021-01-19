///*
// * 文 件 名:  ThirdServiceFactory.java
// * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
// * 描    述:  <描述>
// * 修 改 人:  yangchuan
// * 修改时间:  2020年3月3日
// * 跟踪单号:  <跟踪单号>
// * 修改单号:  <修改单号>
// * 修改内容:  <修改内容>
// */
//package com.yqq.third.service;
//
//import com.yqq.commons.model.RightsHandleResult;
//import com.yqq.framework.web.result.JSONResult;
//import com.yqq.third.dao.RightsMapper;
//import com.yqq.third.enums.OpenRightsTypeEnum;
//import com.yqq.third.model.RightsParam;
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.model.request.wap.RightsLeadReq;
//import com.yqq.third.service.OpenRightsService;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 权益开通工厂
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Component
//public class OpenRightsServiceFactory implements ApplicationContextAware {
//    private Logger logger = LoggerFactory.getLogger(OpenRightsServiceFactory.class);
//
//    private static Map<OpenRightsTypeEnum, OpenRightsService> openRightsServiceMap;
//
//    @Autowired
//    private RightsMapper rightsMapper;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        Map<String,OpenRightsService> map = applicationContext.getBeansOfType(OpenRightsService.class);
//        openRightsServiceMap = new HashMap<>();
//        map.forEach((key,value) -> openRightsServiceMap.put(value.getTypeEnum(),value));
//    }
//
//    public OpenRightsService getOpenRightsService(OpenRightsTypeEnum typeEnum) {
//        return openRightsServiceMap.get(typeEnum);
//    }
//
//    /**
//     *
//     * 接口调用适配
//     * @param userInfoBean 当前开通账号参数
//     * @see OpenRightsTypeEnum
//     * @return 不同权益开通后返回参数暂时只返回文字说明开通成功，后期根据业务需求做调整
//     */
//    public JSONResult<List<RightsHandleResult>> openRights(List<RightsLeadReq> rightsLeadReqs){
//        JSONResult<List<RightsHandleResult>> jsonResult = new JSONResult<>();
//        List<RightsHandleResult> handleResults = new ArrayList<RightsHandleResult>();
//            if(ObjectUtils.isEmpty(rightsLeadReqs)) {
//                jsonResult.setSuccessInfo(handleResults, "开通权益错误,请求信息为空");
//                return jsonResult;
//            }
//            for(RightsLeadReq rightsLeadReq : rightsLeadReqs) {
//                RightsHandleResult handleResult = new RightsHandleResult();
//                handleResult.setDrawNum(rightsLeadReq.getDrawNum());
//                try
//                {
//                    UserInfoBean userInfoBean = rightsLeadReq.getUserInfoBean();
//                    if(userInfoBean == null) {
//                        handleResult.setHandleMsg("开通权益类型错误,用户不允许为空");
//                        handleResult.setHandleStatus(RightsHandleResult.FAIL);
//                        handleResults.add(handleResult);
//                        continue;
//                    }
//                    String mobile = userInfoBean.getMobile();
//                    if(StringUtils.isEmpty(mobile)) {
//                        handleResult.setHandleMsg("开通权益类型错误,用户手机号不允许为空");
//                        handleResult.setHandleStatus(RightsHandleResult.FAIL);
//                        handleResults.add(handleResult);
//                        continue;
//                    }
//                    String rightsNum = rightsLeadReq.getRightsNum();
//                    if(StringUtils.isEmpty(rightsNum)) {
//                        logger.warn("{}========================开通权益类型错误,joinNum 为空", mobile);
//                        handleResult.setHandleMsg("开通权益类型错误,joinNum 为空");
//                        handleResult.setHandleStatus(RightsHandleResult.FAIL);
//                        handleResults.add(handleResult);
//                        continue;
//                    }
//
//                    //权益接入码
//                    String joinNum = rightsMapper.getRightsJoinNum(rightsNum);
//                    if(StringUtils.isEmpty(joinNum)) {
//                        logger.warn("{}========================权益开通 权益编码  ============={}", mobile, rightsNum);
//                        handleResult.setHandleMsg("权益无对应接入码无法开通");
//                        handleResult.setHandleStatus(RightsHandleResult.FAIL);
//                        handleResults.add(handleResult);
//                        continue;
//                    }
//                    logger.info("权益开通接入码 ============={}", joinNum);
//                    OpenRightsTypeEnum typeEnum = this.getOpenRightsTypeEnum(joinNum);
//
//                    if(typeEnum.equals(OpenRightsTypeEnum.R_NULL)) {
//                        logger.warn("{}========================接口不支持该权益==========={}", mobile, joinNum);
//                        handleResult.setHandleMsg("接口不支持该权益");
//                        handleResult.setHandleStatus(RightsHandleResult.FAIL);
//                        handleResults.add(handleResult);
//                        continue;
//                    }
//
//                    //查询权益参数
//                    List<RightsParam> rightsParams = rightsMapper.queryRightsParamList(rightsNum);
//                    if (rightsParams.isEmpty() && !typeEnum.equals(OpenRightsTypeEnum.R_MT)) {
//                        logger.warn("{}========================开通权益参数配置错误", mobile);
//                        handleResult.setHandleMsg("开通权益参数配置错误");
//                        handleResult.setHandleStatus(RightsHandleResult.FAIL);
//                        handleResults.add(handleResult);
//                        continue;
//                    }
//                    OpenRightsService openRightsService = this.getOpenRightsService(typeEnum);
//                    handleResult = openRightsService.openRights(rightsLeadReq, rightsParams);
//                    handleResults.add(handleResult);
//                }catch (Exception e) {
//                    logger.error("权益开通失败  ============={}", e);
//                    handleResult.setHandleStatus(RightsHandleResult.EXCEPT);
//                    handleResult.setHandleMsg("开通异常");
//                    handleResults.add(handleResult);
//                    continue;
//                }
//            }
//            jsonResult.setSuccessInfo(handleResults, "开通信息");
//            return jsonResult;
//    }
//
//    /**
//     *
//     * 根据 请求类型对照调用接口
//     * @param typeNum
//     * @return
//     */
//    public OpenRightsTypeEnum getOpenRightsTypeEnum(String typeNum) {
//        switch (typeNum) {
//            case "SCGJQJK":
//                return OpenRightsTypeEnum.R_MALL;
//            case "ZKYWBL1":
//                return OpenRightsTypeEnum.R_DISCOUNT;
//            case "KQZX":
//                return OpenRightsTypeEnum.R_UNIVERSAL;
//            case "YXAJK":
//                return OpenRightsTypeEnum.R_MARKET;
//            case "MT":
//                return OpenRightsTypeEnum.R_MT;
//            case "ZYHLW":
//                return OpenRightsTypeEnum.R_INTERNET;
//// TODO 其他权益添加
////            case "R_TV":
////                return OpenRightsTypeEnum.R_TV;
////            case "R_FOREIGN":
////                return OpenRightsTypeEnum.R_FOREIGN;
//            default:
//                return OpenRightsTypeEnum.R_NULL;
//        }
//    }
//
//}

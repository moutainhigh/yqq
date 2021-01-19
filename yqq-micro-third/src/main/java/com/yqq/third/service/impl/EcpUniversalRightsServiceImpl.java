///*
// * 文 件 名:  EcpUniversalRightsServiceImpl.java
// * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
// * 描    述:  <描述>
// * 修 改 人:  yangchuan
// * 修改时间:  2020年3月4日
// * 跟踪单号:  <跟踪单号>
// * 修改单号:  <修改单号>
// * 修改内容:  <修改内容>
// */
//package com.yqq.third.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yqq.commons.model.RightsHandleResult;
//import com.yqq.commons.service.BaseService;
//import com.yqq.framework.utils.JacksonUtils;
//import com.yqq.framework.web.result.JSONResult;
//import com.yqq.third.enums.OpenRightsTypeEnum;
//import com.yqq.third.model.RightsParam;
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.model.request.wap.RightsLeadReq;
//import com.yqq.third.service.OpenRightsService;
//import com.yqq.third.utils.BaseEcp;
//import com.yqq.third.utils.ECPUtils;
//import com.yqqh.xwecp.service.logic.client_impl.common.IUniversalVoucherCountService;
//import com.yqqh.xwecp.service.logic.client_impl.common.IUniversalVoucherPreCheckService;
//import com.yqqh.xwecp.service.logic.client_impl.common.IUserCouponsDealService;
//import com.yqqh.xwecp.service.logic.client_impl.common.impl.UniversalVoucherCountServiceClientImpl;
//import com.yqqh.xwecp.service.logic.client_impl.common.impl.UniversalVoucherPreCheckServiceClientImpl;
//import com.yqqh.xwecp.service.logic.client_impl.common.impl.UserCouponsDealServiceClientImpl;
//import com.yqqh.xwecp.service.logic.pojo.DEL610077Result;
//import com.yqqh.xwecp.service.logic.pojo.DEL610097Result;
//import com.yqqh.xwecp.service.logic.pojo.DEL610098Result;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 开通通用券权益实现
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Service
//public class EcpUniversalRightsServiceImpl extends BaseService implements OpenRightsService {
//
//    private IUserCouponsDealService iUserCouponsDealService = new UserCouponsDealServiceClientImpl();
//    private IUniversalVoucherPreCheckService iUniversalVoucherPreCheckService = new UniversalVoucherPreCheckServiceClientImpl();
//    private IUniversalVoucherCountService iUniversalVoucherCountService = new UniversalVoucherCountServiceClientImpl();
//
//    @Override
//    public OpenRightsTypeEnum getTypeEnum() {
//        return OpenRightsTypeEnum.R_UNIVERSAL;
//    }
//
//    /**
//     *
//     * 开通通用券
//     * 测试数据 "991000005006","100000005323","1"
//     * @param userInfoBean
//     * @param parNumber 优惠券id（这里用的父级id，开通时会将父级编码下的所有子集券开通
//     * @param cardNumber
//     * @param isTest
//     */
//    @Override
//    public RightsHandleResult openRights(RightsLeadReq rightsLeadReq, List<RightsParam> rightsParams) {
//        RightsHandleResult jsonResult = new RightsHandleResult();
//        UserInfoBean userInfoBean = rightsLeadReq.getUserInfoBean();
//        String orderNum = rightsLeadReq.getDrawNum();
//        jsonResult.setDrawNum(orderNum);
//        String parNumber = "";
//        String cardNumber = "";
//        String isTest = "1";
//        for (RightsParam param : rightsParams) {
//            String paramNum = param.getParamNum();
//            switch (paramNum) {
//                case "FQBM":
//                    parNumber = param.getParamValue();
//                    break;
//                case "ZQBM":
//                    cardNumber = param.getParamValue();
//                    break;
//                default:
//                    break;
//            }
//        }
//        if (StringUtils.isEmpty(parNumber)) {
//            logger.error("开通通用券权益参数错误 parNumber====== {}", parNumber);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通通用券权益参数错误 parNumber======" + parNumber);
//            return jsonResult;
//        }
//        if (StringUtils.isEmpty(cardNumber)) {
//            logger.error("开通通用券权益参数错误 cardNumber====== {}", cardNumber);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通通用券权益参数错误 cardNumber======" + cardNumber);
//            return jsonResult;
//        }
//        if (StringUtils.isEmpty(isTest)) {
//            logger.error("开通通用券权益参数错误 isTest====== {}", isTest);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通通用券权益参数错误 isTest======" + isTest);
//            return jsonResult;
//        }
//        BaseEcp.generateContextForBusi(orderNum, "开通", userInfoBean);
//        try {
//            JSONResult<String> checkResult = this.checkUniversal(userInfoBean, orderNum, parNumber, cardNumber, isTest);
//            if (checkResult.isSuccess()) {
//                /** 卡券----通用券领取接口 */
//                // 方法中第一个参数表示用户标识（16位数）；第二个参数表示优惠券id（这里用的父级id，开通时会将父级编码下的所有子集券开通）
//                DEL610077Result result = iUserCouponsDealService.userCouponsDeal(userInfoBean.getUserId(), parNumber);
//                ilogger.info("ecp\u0001userCouponsDeal\u0001"+ userInfoBean.getMobile() + "\u0001" +  JacksonUtils.toJSon(rightsParams) + "\u0001" + JacksonUtils.toJSon(result));
//
//                if (ECPUtils.isSuccess(result)) {
//                    logger.info("通用券领取成功信息=============getOrderNo {} getAccessId {} getBossCode {}", result.getOrderNo(), result.getAccessId(), result.getBossCode());
//                    JSONResult<String> contResult = this.universalVoucherCount(userInfoBean, orderNum, parNumber, cardNumber, isTest);
//                    if (!contResult.isSuccess()) {
//                        logger.info("通用券领取后计数失败！getErrorCode{}  getErrorMessage{}",result.getErrorCode(),result.getErrorMessage());
//                        jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                        jsonResult.setHandleMsg("通用券领取后计数失败！getErrorCode "+result.getErrorCode()+" getErrorMessage"+result.getErrorMessage());
//                        return jsonResult;
//                    }
//                    jsonResult.setHandleStatus(RightsHandleResult.SUCCESS);
//                    jsonResult.setHandleMsg("开通通用权益成功");
//                    return jsonResult;
//                } else {
//                    if (null != result) {
//                        logger.error("通用券领取DEL610077,用户号码[" + userInfoBean.getMobile() + "],订单号[" + orderNum + "],错误码:[" + result.getErrorCode() + "],错误信息:[" + result.getErrorMessage() + "]");
//                        jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                        jsonResult.setHandleMsg(result.getErrorMessage());
//                        return jsonResult;
//                    } else {
//                        logger.error("通用券领取DEL610077,用户号码[" + userInfoBean.getMobile() + "],接口范围为空");
//                        jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                        jsonResult.setHandleMsg("通用券领取DEL610077,用户号码[" + userInfoBean.getMobile() + "],接口范围为空");
//                        return jsonResult;
//                    }
//                }
//            }else {
//                logger.error(checkResult.getMessage());
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg(checkResult.getMessage());
//                return jsonResult;
//            }
//        } catch (Exception e) {
//            logger.error("通用券开通DEL610077，用户号码[" + (userInfoBean == null ? "" : userInfoBean.getMobile()) + "]，活动编码[" + orderNum + "]，通过ECP业务办理异常：{}", e);
//            ilogger.info("ecp\u0001userCouponsDeal\u0001"+ userInfoBean.getMobile() + "\u0001" +  JacksonUtils.toJSon(rightsParams) + "\u0001" + e.getMessage());
//            jsonResult.setHandleStatus(RightsHandleResult.EXCEPT);
//            jsonResult.setHandleMsg("开通通用权益失败"+e.getMessage());
//            return jsonResult;
//        }
//    }
//
//    private JSONResult<String> checkUniversal(UserInfoBean userInfoBean, String orderNum, String parNumber,
//            String cardNumber, String isTest) {
//        JSONResult<String> jsonResult = new JSONResult<>();
//        BaseEcp.generateContextForBusi(orderNum, "校验", userInfoBean);
//        // 业务编码,YYQLQ
//        String busiCode = "TYQLQYZ";
//        // 操作类型
//        String optType = "2";
//        try {
//            if (userInfoBean != null) {
//                /** 卡券----通用券校验接口 */
//                DEL610097Result result = iUniversalVoucherPreCheckService.universalVoucherPreCheck(
//                        userInfoBean.getChValue(), userInfoBean.getMobile(), "SZX", userInfoBean.getCityNo(), ECPUtils.getEcpInstanceName(),
//                        busiCode, optType, isTest, parNumber, cardNumber);
//                //日志记录
//                JSONObject jo = new JSONObject();
//                jo.put("orderNum", orderNum);
//                jo.put("parNumber", parNumber);
//                jo.put("cardNumber", cardNumber);
//                jo.put("isTest", cardNumber);
//                ilogger.info("ecp\u0001universalVoucherPreCheck\u0001"+ userInfoBean.getMobile() + "\u0001" + jo.toJSONString() + "\u0001" + JacksonUtils.toJSon(result));
//                if (ECPUtils.isSuccess(result)) {
//                    jsonResult.setSuccessInfo("", "通用权益开通校验成功");
//                }
//                else {
//                    if (null != result) {
//                        logger.error("通用权益开通校验,用户号码[" + userInfoBean.getMobile() + "],活动编码[" + orderNum + "],错误码:["+ result.getErrorCode() + "],错误信息:[" + result.getErrorMessage() + "]");
//                        jsonResult.setErrorInfo(result.getErrorMessage());
//                    }
//                    else {
//                        logger.error("通用权益开通校验,用户号码[" + userInfoBean.getMobile() + "],接口范围为空");
//                        jsonResult.setErrorInfo("通用权益开通校验,用户号码[" + userInfoBean.getMobile() + "],接口范围为空");
//                    }
//                }
//            }
//            return jsonResult;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            logger.error("通用券校验DEL610097，用户号码[" + (userInfoBean == null ? "" : userInfoBean.getMobile()) + "]，活动编码["+ orderNum + "]，通过ECP业务办理异常：", e);
//            jsonResult.setFailInfo("通用权益开通校验失败");
//            return jsonResult;
//        }
//    }
//
//    private JSONResult<String> universalVoucherCount(UserInfoBean userInfoBean, String orderNum, String parNumber,
//            String cardNumber, String isTest) {
//        JSONResult<String> jsonResult = new JSONResult<>();
//        BaseEcp.generateContextForBusi(orderNum, "计数", userInfoBean);
//        // 业务编码,YYQLQ
//        String busiCode = "TYQLQJS";
//        // 操作类型
//        String optType = "2";
//        try {
//            if (userInfoBean != null) {
//                /** 卡券----通用券领取后计数接口 */
//                // 方法中第一个参数表示用户标识（16位数）；第二个参数表示优惠券id（这里用的父级id，开通时会将父级编码下的所有子集券开通）
//                DEL610098Result result = iUniversalVoucherCountService.universalVoucherCount(userInfoBean.getChValue(),
//                        userInfoBean.getMobile(), "SZX", userInfoBean.getCityNo(), ECPUtils.getEcpInstanceName(), busiCode, optType, isTest,
//                        parNumber, cardNumber);
//                //日志记录
//                JSONObject jo = new JSONObject();
//                jo.put("orderNum", orderNum);
//                jo.put("parNumber", parNumber);
//                jo.put("cardNumber", cardNumber);
//                jo.put("isTest", cardNumber);
//                ilogger.info("ecp\u0001universalVoucherCount\u0001"+ userInfoBean.getMobile() + "\u0001" + jo.toJSONString() + "\u0001" + JacksonUtils.toJSon(result));
//                if (ECPUtils.isSuccess(result)) {
//                    jsonResult.setSuccessInfo("", "通用权益开通计数成功");
//                }
//                else {
//                    if (null != result) {
//                        logger.error("通用券领取成功后计数接口DEL610098,用户号码[" + userInfoBean.getMobile() + "],活动编码[" + orderNum+ "],错误码:[" + result.getErrorCode() + "],错误信息:[" + result.getErrorMessage() + "]");
//                        jsonResult.setErrorInfo(result.getErrorMessage());
//                    }
//                    else {
//                        logger.error("通用券领取成功后计数接口DEL610098,用户号码[" + userInfoBean.getMobile() + "],接口范围为空");
//                        jsonResult.setErrorInfo("通用券领取成功后计数异常");
//                    }
//                }
//            }
//            return jsonResult;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            logger.error("通用券开通后计数DEL610098，用户号码[" + (userInfoBean == null ? "" : userInfoBean.getMobile()) + "]，活动编码["+ orderNum + "]，通过ECP业务办理异常：", e);
//            jsonResult.setErrorInfo("通用券开通后计数DEL610098，用户号码[" + (userInfoBean == null ? "" : userInfoBean.getMobile()) + "]，活动编码["+ orderNum + "]，通过ECP业务办理异常："+e);
//            return jsonResult;
//        }
//    }
//}

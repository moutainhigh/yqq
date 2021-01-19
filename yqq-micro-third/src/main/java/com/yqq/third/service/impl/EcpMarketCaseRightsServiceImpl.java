///*
// * 文 件 名:  EcpMarketCaseRightsServiceImpl.java
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
//import com.yqq.commons.model.RightsHandleResult;
//import com.yqq.commons.service.BaseService;
//import com.yqq.framework.utils.JacksonUtils;
//import com.yqq.third.enums.OpenRightsTypeEnum;
//import com.yqq.third.model.RightsParam;
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.model.request.wap.RightsLeadReq;
//import com.yqq.third.service.OpenRightsService;
//import com.yqq.third.utils.BaseEcp;
//import com.yqq.third.utils.ECPUtils;
//import com.yqqh.xwecp.service.logic.LIException;
//import com.yqqh.xwecp.service.logic.client_impl.common.ITransactYxfaService;
//import com.yqqh.xwecp.service.logic.client_impl.common.impl.TransactYxfaServiceClientImpl;
//import com.yqqh.xwecp.service.logic.pojo.DEL060001Result;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 营销案权益开通实现
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Service
//public class EcpMarketCaseRightsServiceImpl extends BaseService implements OpenRightsService {
//
//    ITransactYxfaService iTransactYxfaService = new TransactYxfaServiceClientImpl();
//
//    @Override
//    public OpenRightsTypeEnum getTypeEnum() {
//        return OpenRightsTypeEnum.R_MARKET;
//    }
//
//    /**
//     *
//     * 营销案权益开通
//     * 测试数据 "300004079945","12997017"
//     * @param userInfoBean
//     * @param gradeCode 档次编码/二级编码
//     * @param giftCode 礼品编码
//     * @param giftPackCode 礼品包编码
//     */
//    @Override
//    public RightsHandleResult openRights(RightsLeadReq rightsLeadReq, List<RightsParam> rightsParams) {
//        RightsHandleResult jsonResult = new RightsHandleResult();
//        UserInfoBean userInfoBean = rightsLeadReq.getUserInfoBean();
//        String orderNum = rightsLeadReq.getDrawNum();
//        jsonResult.setDrawNum(orderNum);
//        String gradeCode = "";
//        String giftPackCode = "";
//
//        for (RightsParam param : rightsParams) {
//            String paramNum = param.getParamNum();
//            switch (paramNum) {
//                case "RJYXABM":
//                    gradeCode = param.getParamValue();
//                    break;
//                case "JPBBM":
//                    giftPackCode = param.getParamValue();
//                    break;
//                case "JPBM":
//                    giftPackCode = giftPackCode + "|" + param.getParamValue();
//                    break;
//                default:
//                    break;
//            }
//        }
//        if(StringUtils.isEmpty(gradeCode)) {
//            logger.error("开通营销案权益参数错误 gradeCode====== {}",gradeCode);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通营销案权益参数错误 gradeCode======" + gradeCode);
//            return jsonResult;
//        }
//        if(StringUtils.isEmpty(giftPackCode)) {
//            logger.error("开通营销案权益参数错误 giftPackCode====== {}",giftPackCode);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通营销案权益参数错误 giftPackCode======" + giftPackCode);
//            return jsonResult;
//        }
//
//        logger.info("营销案开通调用=============getMobile {} gradeCode {} giftPackCode {} ",userInfoBean.getMobile(), gradeCode,giftPackCode);
//        try {
//            BaseEcp.generateContextForBusi(orderNum, "1", userInfoBean);
//            if (StringUtils.isNotEmpty(userInfoBean.getUserId())) {
//
//                // 入参：充值类别（1非充值类，4充值类）、地市编码（11~23）、用户唯一标识、充值类型（1充值，0非充值）、银联号、业务编码、礼品编码、金额、档次二级编码、礼品包编码
//                DEL060001Result ecpResult = iTransactYxfaService.transactYxfa("1",
//                        userInfoBean.getCityNo(),
//                        userInfoBean.getUserId(),
//                        0,
//                        "",
//                        "",
//                        "",
//                        "",
//                        gradeCode,
//                        giftPackCode);
//                ilogger.info("ecp\u0001transactYxfa\u0001"+ userInfoBean.getMobile() + "\u0001" + JacksonUtils.toJSon(rightsParams) + "\u0001" + JacksonUtils.toJSon(ecpResult));
//                if (ECPUtils.isSuccess(ecpResult)) {
//                    logger.info("营销案开通成功=============getOrderNo {} getAccessId {} getBossCode {}",ecpResult.getOrderNo(),ecpResult.getAccessId(), ecpResult.getBossCode());
//                    jsonResult.setHandleStatus(RightsHandleResult.SUCCESS);
//                    jsonResult.setHandleMsg("营销案开通成功");
//                }else {
//                    if (null != ecpResult) {
//                        logger.error("营销案开通DEL060001,用户号码[" + userInfoBean.getMobile() + "],订单编码[" + orderNum + "],错误码：[" + ecpResult.getErrorCode() + "],错误信息:["
//                                + ecpResult.getErrorMessage() + "]");
//                        jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                        jsonResult.setHandleMsg(ecpResult.getErrorMessage().replaceAll("\r|\n", ""));
//                    } else {
//                        logger.error("营销案开通DEL060001,用户号码[" + userInfoBean.getMobile() + "],接口范围为空");
//                        jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                        jsonResult.setHandleMsg("营销案开通失败，接口返回为空");
//                    }
//                }
//            }
//            return jsonResult;
//        } catch (LIException e) {
//            logger.info("营销案开通DEL060001，用户号码[" + userInfoBean.getMobile() + "],订单编码[" + orderNum + "],通过ECP业务查询异常：====={}", e);
//            ilogger.info("ecp\u0001transactYxfa\u0001"+ userInfoBean.getMobile() + "\u0001" + JacksonUtils.toJSon(rightsParams) + "\u0001" + e.getMessage());
//            jsonResult.setHandleStatus(RightsHandleResult.EXCEPT);
//            jsonResult.setHandleMsg("营销案开通异常");
//            return jsonResult;
//        }
//    }
//}

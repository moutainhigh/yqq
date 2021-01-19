///*
// * 文 件 名:  EcpForeignRightsServiceImpl.java
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
//import com.yqqh.xwecp.service.logic.client_impl.common.IRegularCouponService;
//import com.yqqh.xwecp.service.logic.client_impl.common.impl.RegularCouponServiceClientImpl;
//import com.yqqh.xwecp.service.logic.pojo.DEL010037Result;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 异业券权益开通 实现
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Service
//public class EcpForeignRightsServiceImpl extends BaseService implements OpenRightsService {
//
//    private IRegularCouponService iRegularCouponService = new RegularCouponServiceClientImpl();
//
//    @Override
//    public OpenRightsTypeEnum getTypeEnum() {
//        return OpenRightsTypeEnum.R_FOREIGN;
//    }
//
//    /**
//     * 开通异业券 权益
//     * 测试数据 "fq9900135422","zq9900003728"
//     * @param userInfoBean
//     * @param parNumber 父券编码
//     * @param cardNumber 子券编码
//     * @param isTest 是否测试：0 测试 1 非测试
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
//
//        if(StringUtils.isEmpty(parNumber)) {
//            logger.error("开通异业券 权益参数错误 parNumber====== {}",parNumber);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通异业券 权益参数错误 parNumber======" + parNumber);
//            return jsonResult;
//        }
//        if(StringUtils.isEmpty(cardNumber)) {
//            logger.error("开通异业券 权益参数错误 cardNumber====== {}",cardNumber);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通异业券 权益参数错误 cardNumber======" + cardNumber);
//            return jsonResult;
//        }
//        if(StringUtils.isEmpty(isTest)) {
//            logger.error("开通异业券 权益参数错误 isTest====== {}",isTest);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通异业券 权益参数错误 isTest======" + isTest);
//            return jsonResult;
//        }
//
//        try {
//            BaseEcp.generateContextForBusi(orderNum, "4", userInfoBean);
//            // 异业券领取接口
//            DEL010037Result del010037Result = iRegularCouponService.regularCoupon(
//                    userInfoBean.getMobile(),
//                    "QQT", //"QQT"
//                    userInfoBean.getCityNo(),
//                    "wap", //写死 wap
//                    "CXKLTYQ",//写死 "CXKLTYQ"
//                    isTest,
//                    parNumber,
//                    cardNumber,
//                    "");
//            ilogger.info("ecp\u0001regularCoupon\u0001"+ userInfoBean.getMobile() + "\u0001" + JacksonUtils.toJSon(rightsParams) + "\u0001" + JacksonUtils.toJSon(del010037Result));
//            if (ECPUtils.isSuccess(del010037Result)) {
//                jsonResult.setHandleStatus(RightsHandleResult.SUCCESS);
//                jsonResult.setHandleMsg("开通异业券成功");
//            } else {
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg(del010037Result.getErrMsg());
//            }
//            return jsonResult;
//        } catch (Exception e) {
//            logger.error("@@@@@@Error-regularCouponServiceClientImpl-regularCoupon: parNumber= {} @ cardNumber= {} " , parNumber, cardNumber);
//            ilogger.info("ecp\u0001regularCoupon\u0001"+ userInfoBean.getMobile() + "\u0001" + JacksonUtils.toJSon(rightsParams) + "\u0001" + e.getMessage());
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("异业券领取接口请求异常" + e.getMessage());
//            return jsonResult;
//        }
//    }
//}

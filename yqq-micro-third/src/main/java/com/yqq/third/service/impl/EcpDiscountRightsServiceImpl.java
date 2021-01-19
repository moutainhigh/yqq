///*
// * 文 件 名:  EcpDiscountRightsServiceImpl.java
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
//import com.yqq.xwecp.service.logic.LIException;
//import com.yqq.xwecp.service.logic.client.XWECPLIClient;
//import com.yqq.xwecp.service.logic.client_impl.common.ITransactBusinessService;
//import com.yqq.xwecp.service.logic.client_impl.common.impl.TransactBusinessServiceClientImpl;
//import com.yqq.xwecp.service.logic.pojo.DEL010001Result;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * 折扣权益开通实现
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Service
//public class EcpDiscountRightsServiceImpl extends BaseService implements OpenRightsService {
//
//    private ITransactBusinessService iTransactBusinessService = new TransactBusinessServiceClientImpl();
//
//    @Autowired
//    private  HttpServletRequest request;
//
//    @Override
//    public OpenRightsTypeEnum getTypeEnum() {
//        return OpenRightsTypeEnum.R_DISCOUNT;
//    }
//
//    /**
//     *
//     * 重载方法
//     * 测试数据      "LLKCB_10Y500M","0.1"
//     * @param userInfoBean
//     * @param orderNum
//     * @param rightsParams  ZKBM 业务编码   ZK 折扣
//     * @return
//     */
//    @Override
//    public RightsHandleResult openRights(RightsLeadReq rightsLeadReq, List<RightsParam> rightsParams) {
//        RightsHandleResult jsonResult = new RightsHandleResult();
//        String busiNum = "";
//        String discount = "";
//        UserInfoBean userInfoBean = rightsLeadReq.getUserInfoBean();
//        jsonResult.setDrawNum(rightsLeadReq.getDrawNum());
//        for (RightsParam param : rightsParams) {
//            String paramNum = param.getParamNum();
//            switch (paramNum) {
//                //折扣编码
//                case "ZKBM":
//                    busiNum = param.getParamValue();
//                    break;
//                //折扣
//                case "ZK":
//                    discount = param.getParamValue();
//                    break;
//                default:
//                    break;
//            }
//        }
//        // 折扣信息
//        String reserve1 = "";
//        if(StringUtils.isNotEmpty(discount)) {
//            reserve1 = "900000000660=" + discount + "=PCOpRec";
//        }
//        if (StringUtils.isEmpty(busiNum)) {
//            logger.error("开通折扣权益参数错误 busiNum====== {}", busiNum);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通折扣权益参数错误 busiNum======" + busiNum);
//            return jsonResult;
//        }
//        // 流量快餐包
//        int oprType = 1; // 1 开；2 关
//        int chooseFlag = 1; // 生效方式 1 立即
//        String beginDate = "";
//        String endDate = "";
//        try {
//            BaseEcp.generateContextForBusi(rightsLeadReq.getDrawNum(), "1",userInfoBean);
//            XWECPLIClient.setRequest(request);
//            DEL010001Result del010001Result = iTransactBusinessService.transactBusiness(
//                    userInfoBean.getMobile(),
//                    busiNum,
//                    oprType,
//                    chooseFlag,
//                    beginDate,
//                    endDate,
//                    reserve1,
//                    "");
//            ilogger.info("ecp\u0001transactBusiness\u0001"+ userInfoBean.getMobile() + "\u0001" + JacksonUtils.toJSon(rightsParams) + "\u0001" + JacksonUtils.toJSon(del010001Result));
//            if (ECPUtils.isSuccess(del010001Result)) {
//                logger.info("开通折扣权益成功 DEL010001Result : " + "phone=" + userInfoBean.getMobile() + "@" + "busiNum=" + busiNum + "orderNum" + del010001Result.getOrderNo());
//                jsonResult.setHandleStatus(RightsHandleResult.SUCCESS);
//                jsonResult.setHandleMsg("开通折扣权益成功" + del010001Result.getOrderNo());
//            } else {
//                logger.info("开通折扣权益失败 DEL010001Result : " + "phone=" + userInfoBean.getMobile() + "@" + "busiNum=" + busiNum + "错误信息" + del010001Result.getErrorMessage());
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg(del010001Result.getErrorMessage());
//            }
//            return jsonResult;
//        } catch (LIException e) {
//            e.printStackTrace();
//            logger.error("@@@@@@Error-businessServiceClientImpl-transactBusiness: " + "phone=" + userInfoBean.getMobile() + "@" + "busiNum=" + busiNum+ "exception: ", e);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通折扣权益请求异常"+ e.getMessage());
//            return jsonResult;
//        }
//    }
//}

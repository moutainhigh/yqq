///*
// * 文 件 名:  ICRightsServiceImpl.java
// * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
// * 描    述:  <描述>
// * 修 改 人:  yangchuan
// * 修改时间:  2020年3月11日
// * 跟踪单号:  <跟踪单号>
// * 修改单号:  <修改单号>
// * 修改内容:  <修改内容>
// */
//package com.yqq.third.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yqq.commons.model.RightsHandleResult;
//import com.yqq.commons.service.BaseService;
//import com.yqq.framework.utils.DateTimeUtils;
//import com.yqq.framework.utils.JacksonUtils;
//import com.yqq.framework.web.result.JSONResult;
//import com.yqq.third.enums.OpenRightsTypeEnum;
//import com.yqq.third.model.RightsParam;
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.model.request.wap.RightsLeadReq;
//import com.yqq.third.service.InternetCService;
//import com.yqq.third.service.OpenRightsService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.DigestUtils;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
///**
// * 互联网中心权益开通接口
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Service
//public class InternetCRightsServiceImpl extends BaseService implements OpenRightsService {
//
//    @Value("${xw.internet.url}")
//    String internetUrl;
//
//    /**
//    * 办理接口私钥
//    */
//   private final static String ORDERACCEPT_PRIVATEKEY = "AF1351388FCA73A1";
//   /**
//    * 渠道号
//    */
//   private final static String CHANNEL_CODE = "70741";
//   /**
//    * 办理接口appid
//    */
//   private final static String ORDERACCEPT_APIID = "303";
////
////   /**
////    * 办理接口私钥
////    */
////   private final static String ORDERACCEPT_PRIVATEKEY = "bA0NRASDc372mvx9";
////   /**
////    * 渠道号
////    */
////   private final static String CHANNEL_CODE = "67039";
////   /**
////    * 办理接口appid
////    */
////   private final static String ORDERACCEPT_APIID = "30001";
//
//   @Autowired
//   private RestTemplate restTemplate;
//
//   @Autowired
//   private InternetCService internetCService;
//
//    @Override
//    public OpenRightsTypeEnum getTypeEnum() {
//        return OpenRightsTypeEnum.R_INTERNET;
//    }
//
//    /**
//     * 开通互联网中心权益
//     * <pre>
//     * 测试数据 salesId：   101972 salesName：哈啰出行-哈啰单车-骑行月卡      prodId：   6000642 prodName：哈啰出行-哈啰单车-骑行月卡
//     * 请求体样例，无需支付支付信息无需传值 json 格式 body: {"channelNo":"2001807130001","phone":"13712341234","salesId":"2018001","salesName":"权益包","acceptTime":"20180712","amount":"0","clentIp":"10.10.10.10","dealType":"01","isPay":"0","prodInfo":[{"prodId":"75001","prodName":"7天权益包","serverNum":"13712341234","quantity":"1","extField":"xxxx"}],"payInfo":{"payType":"01","appid":"001","payWay":"WWW","wxOpenid":"xxxxx","returnUrl":"http://xxxxx.com","period":"30","periodUnit":"00","merchantName":"爱奇艺","salesDesc":"周卡","reserved1":"XXXX","salesURL":"XXXXX"}}
//     * </pre>
//     * @param userInfoBean
//     * @param orderNum
//     * @param rightsParams
//     * @return
//     */
//    @Override
//    public RightsHandleResult openRights(RightsLeadReq rightsLeadReq, List<RightsParam> rightsParams) {
//        RightsHandleResult jsonResult = new RightsHandleResult();
//        UserInfoBean userInfoBean = rightsLeadReq.getUserInfoBean();
//        String orderNum = rightsLeadReq.getDrawNum();
//        jsonResult.setDrawNum(orderNum);
//
//        String reqTime = DateTimeUtils.getTodayChar17();
//        String acceptTime = DateTimeUtils.getTodayChar14();
//
//        try {
//            String phone = userInfoBean.getMobile();
//            String salesId = "";
//            String salesName = "";
//            String prodId =  "";
//            String prodName = "";
//            String quantity = rightsLeadReq.getLeadCount().toString();
//            String isCheck = "";
//            for(RightsParam param : rightsParams) {
//               String paramNum = param.getParamNum();
//               switch (paramNum) {
//                   case "SALESID":
//                       salesId = param.getParamValue();
//                       break;
//                   case "SALESNAME":
//                       salesName = param.getParamValue();
//                       break;
//                   case "PRODID":
//                       prodId = param.getParamValue();
//                       break;
//                   case "PRODNAME":
//                       prodName = param.getParamValue();
//                       break;
//                   case "ISCHECK":
//                       isCheck = param.getParamValue();
//                       break;
//                   default:
//                       break;
//               }
//            }
//
//            if(StringUtils.isEmpty(orderNum)) {
//                logger.error("开通互联网中心权益参数错误 orderNum====== {}",orderNum);
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg("开通互联网中心权益错误 orderNum======" + orderNum);
//                return jsonResult;
//            }
//            if(StringUtils.isEmpty(salesId)) {
//                logger.error("开通互联网中心权益参数错误 salesId====== {}",salesId);
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg("开通互联网中心权益错误 salesId======" + salesId);
//                return jsonResult;
//            }
//            if(StringUtils.isEmpty(salesName)) {
//                logger.error("开通互联网中心权益参数错误 salesName====== {}",salesName);
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg("开通互联网中心权益错误 salesName======" + salesName);
//                return jsonResult;
//            }
//            if(StringUtils.isEmpty(prodId)) {
//                logger.error("开通互联网中心权益参数错误 prodId====== {}",prodId);
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg("开通互联网中心权益错误 prodId======" + prodId);
//                return jsonResult;
//            }
//            if(StringUtils.isEmpty(prodName)) {
//                logger.error("开通互联网中心权益参数错误 prodName====== {}",prodName);
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg("开通互联网中心权益错误 prodName======" + prodName);
//                return jsonResult;
//            }
//            //开通预校验
//            if("1".equals(isCheck)) {
//                JSONResult<Object> jsonResult2 = internetCService.queryIsOpenRights(userInfoBean, salesId, prodId);
//                if(!jsonResult2.isSuccess()) {
//                    logger.error("phone {} 开通互联网中心权益预校验错误 {}", phone, jsonResult2.getMessage());
//                    jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                    jsonResult.setHandleMsg("开通互联网中心权益预校验错误" + jsonResult2.getMessage());
//                    return jsonResult;
//                }
//                JSONObject jsonObject =  JSONObject.parseObject(jsonResult2.getData().toString());
//                // 0表示可以开通，1表示不能开通， 2表示重复开通，3表示业务互斥
//                String queryProdResultCode = jsonObject.getString("queryProdResultCode");
//                if(!"0".equals(queryProdResultCode)) {
//                    logger.error("phone {} 开通互联网中心权益预校验失败 {} ", phone, jsonObject.getString("queryProdResultMsg"));
//                    jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                    jsonResult.setHandleMsg("开通互联网中心权益预校验错误" + jsonObject.getString("queryProdResultMsg"));
//                    return jsonResult;
//                }
//            }
//            //0-订购,1-退订,2-兑换 此处默认订购
//            String dealType = "0";
//
//            JSONObject body = new JSONObject();
//            body.put("channelNo",orderNum);
//            body.put("phone", phone);
//            body.put("salesId",salesId);
//            body.put("salesName",salesName);
//            body.put("dealType",dealType);
//            body.put("acceptTime",acceptTime);
//            //价格
//            //body.put("amount", 0);
//            //是否需要支付 0 不需要 1 需要
//            body.put("isPay","0");
//            //0表示不参加营销活动
//            body.put("marketingCode","0");
//
//            JSONObject prodInfo = new JSONObject();
//            prodInfo.put("prodId", prodId);
//            prodInfo.put("prodName", prodName);
//            prodInfo.put("serverNum", phone);
//            prodInfo.put("quantity", quantity);
//
//            body.put("prodInfo", prodInfo);
//
//            //extField 本业务暂时不需要 购买的销售品中如果含有特殊处理信息（详见同步产品接口内容） "extField":"{\"numType\":\"1\",\"num\":\"12312312312\"}"
//
//            //生成签名
//            String digitalSign = this.getDigitalSign(orderNum, reqTime, body);
//
//            JSONObject head = new JSONObject();
//            head.put("apiId", ORDERACCEPT_APIID);
//            head.put("channelCode", CHANNEL_CODE);
//            head.put("transactionId", orderNum);
//            head.put("reqTime",reqTime);
//            head.put("sign", digitalSign);
//            head.put("version","1.0");
//
//            JSONObject req1 = new JSONObject();
//            req1.put("head",head);
//            req1.put("body",body);
//
//            JSONObject req = new JSONObject();
//            req.put("contractRoot", req1);
//            logger.info("请求信息 {}", req.toJSONString());
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Accept", "application/json; charset=UTF-8");
//            headers.add("Content-Type", "application/json");
//
//            HttpEntity<JSONObject> requestEntity = new HttpEntity<>(req, headers);
//            ResponseEntity<String> responseEntity = restTemplate.exchange(internetUrl + "/cmpp-api/external/v3/orderAccept", HttpMethod.POST, requestEntity, String.class);
//            String resBody = responseEntity.getBody();
//
//            ilogger.info("internet\u0001orderAccept\u0001"+ userInfoBean.getMobile() + "\u0001" +  JacksonUtils.toJSon(rightsParams) + "\u0001" + resBody);
//
//            JSONObject allJson = JSONObject.parseObject(resBody);
//            logger.info("返回信息 {}", allJson.toJSONString());
//            JSONObject rootJson = JSONObject.parseObject(allJson.getString("contractRoot"));
//            JSONObject bodyJson = JSONObject.parseObject(rootJson.getString("body"));
//
//            if("0000".equals(bodyJson.getString("resultCode"))){
//                //开通成功查询开通信息
//                JSONResult<Object> jsonResult3 = internetCService.queryOrders(userInfoBean, orderNum, "", "");
//                if(!jsonResult3.isSuccess()) {
//                    jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                    jsonResult.setHandleMsg("开通成功,查询结果失败"+jsonResult3.getMessage());
//                    return jsonResult;
//                }
//                jsonResult.setHandleStatus(RightsHandleResult.SUCCESS);
//                jsonResult.setHandleMsg(bodyJson.getString("orderNo"));
//                return jsonResult;
//            } else {
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg("提示信息:" + bodyJson.getString("resultMsg")+ "错误码:" + bodyJson.getString("resultCode"));
//                return jsonResult;
//            }
//        } catch (Exception e) {
//            logger.info("开通互联网中心权益 失败，用户号码[" + userInfoBean.getMobile() + "] =============={}", e);
//            ilogger.info("internet\u0001orderAccept\u0001"+ userInfoBean.getMobile() + "\u0001" + JacksonUtils.toJSon(rightsParams) + "\u0001" + e.getMessage());
//            jsonResult.setHandleStatus(RightsHandleResult.EXCEPT);
//            jsonResult.setHandleMsg("开通互联网中心权益失败"+e.getMessage());
//            return jsonResult;
//        }
//    }
//
//
//    /**
//     *
//     * 根据参数生成数字签名
//     * @param transactionId 业务流水id
//     * @param saleID 业务编码
//     * @return
//     */
//    private String getDigitalSign(String transactionId, String reqTime, JSONObject body) {
//        // 签名格式 transactionId + reqTime + PRIVATEKEY+【body内容】
//        StringBuffer sb = new StringBuffer();
//        sb.append(transactionId);
//        sb.append(reqTime);
//        sb.append(ORDERACCEPT_PRIVATEKEY);
//        sb.append(body.toJSONString());
//        String signParam = sb.toString();
//        logger.info("数字签名 {}", signParam);
//        String sign = DigestUtils.md5DigestAsHex(signParam.getBytes()).toUpperCase();
//        return sign;
//    }
//}

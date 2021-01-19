///*
// * 文 件 名:  OpenMallRightsServiceImpl.java
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
//import com.ice.utils.IceUtils;
//import com.yqq.commons.model.RightsHandleResult;
//import com.yqq.commons.service.BaseService;
//import com.yqq.framework.utils.JacksonUtils;
//import com.yqq.third.enums.OpenRightsTypeEnum;
//import com.yqq.third.model.RightsParam;
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.model.request.wap.RightsLeadReq;
//import com.yqq.third.service.OpenRightsService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 开通商城购机权益 实现
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Service
//public class MallRightsServiceImpl extends BaseService implements OpenRightsService {
//
//    @Value("${xw.mall.url}")
//    String mallUrl;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    /**
//     *
//     * 开通商城购机权益
//     * 测试数据   "429AF3F613A84BA996DE33849E5655FD@@40E98C0097524C8D8076A0DC1335ED88"
//     * @param userInfoBean
//     * @param orderNum
//     * @param rightsParams  YHQBM 业务编码
//     * @return
//     */
//    @Override
//    public RightsHandleResult openRights(RightsLeadReq rightsLeadReq, List<RightsParam> rightsParams) {
//        RightsHandleResult jsonResult = new RightsHandleResult();
//        UserInfoBean userInfoBean = rightsLeadReq.getUserInfoBean();
//        jsonResult.setDrawNum(rightsLeadReq.getDrawNum());
//
//        String couponCfgNum = "";
//        for(RightsParam param : rightsParams) {
//           String paramNum = param.getParamNum();
//            switch (paramNum) {
//                //优惠券编码
//                case "YHQBM":
//                    couponCfgNum = param.getParamValue();
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        if(StringUtils.isEmpty(couponCfgNum)) {
//            logger.error("开通商城购机权益参数错误 couponCfgNum====== {}",couponCfgNum);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通商城购机权益参数错误 couponCfgNum======" + couponCfgNum);
//            return jsonResult;
//        }
//
//        try {
//            String mobile = userInfoBean.getMobile();
//            String response1 = this.callInterface(mobile, couponCfgNum);
//
//            ilogger.info("mall\u0001mall\u0001"+ userInfoBean.getMobile() + "\u0001" + JacksonUtils.toJSon(rightsParams) + "\u0001" + response1);
//
//            logger.info("OpenMallRightsService 接口返回信息: " + "phone=" + userInfoBean.getMobile() + "@" + "response1=" + response1);
//            JSONObject responseJson1 = JSONObject.parseObject(response1);
//            String resCode1 = responseJson1.getString("resCode");
//            if ("1".equals(resCode1)) {
//               jsonResult.setHandleStatus(RightsHandleResult.SUCCESS);
//               jsonResult.setHandleMsg("开通商城权益成功");
//            } else {
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg("商城购机券领取失败:" + responseJson1.get("resMsg").toString());
//            }
//            return jsonResult;
//        } catch (Exception e) {
//            logger.error("@@@@@@Error-OpenMallRightsService: " + "phone=" + userInfoBean.getMobile() + "@" + "couponCfgNum=" + couponCfgNum +"exception", e);
//            ilogger.info("mall\u0001mall\u0001"+ userInfoBean.getMobile() + "\u0001" + JacksonUtils.toJSon(rightsParams) + "\u0001" + e.getMessage());
//            jsonResult.setHandleStatus(RightsHandleResult.EXCEPT);
//            jsonResult.setHandleMsg("商城购机券领取接口接口请求异常"+ e.getMessage());
//            return jsonResult;
//        }
//    }
//
//    @Override
//    public OpenRightsTypeEnum getTypeEnum() {
//        return OpenRightsTypeEnum.R_MALL;
//    }
//    /**
//     *
//     * 根据参数生成签名
//     * @param mobile
//     * @param param
//     * @return
//     */
//    private String getSign(String mobile, String param){
//        Map<String,String> map = new HashMap<>();
//        map.put("mobile", mobile);//手机号 前台传的
//        map.put("method", "coupon.gain");//传入调用方法
//        map.put("appKey", "appuser");//固定写死
//        map.put("v", "1.0");//固定写死
//        map.put("format", "json");//固定写死
//        map.put("token", "0c7b7c12b72249a3b36de53d9a3e0f21");//固定写死
//        map.put("locale", "zh_CN");//固定写死
//        map.put("couponCfgNum", param);//优惠券配置编码  前台传的
//        String sign = IceUtils.sign(map, "BCDWDewb89023fb3keb2axZSD");
//        return sign;
//    }
//
//    /**
//     *
//     * 根据参数请求接口
//     * @param mobile
//     * @param param
//     * @return
//     */
//    private String callInterface(String mobile, String param){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("mobile", mobile);//手机号 前台传的
//        params.add("method", "coupon.gain");//传入调用方法
//        params.add("appKey", "appuser");//固定写死
//        params.add("v", "1.0");//固定写死
//        params.add("format", "json");//固定写死
//        params.add("token", "0c7b7c12b72249a3b36de53d9a3e0f21");//固定写死
//        params.add("locale", "zh_CN");//固定写死
//        params.add("couponCfgNum", param);//优惠券配置编码  前台传的
//        String sign = this.getSign(mobile, param);
//        params.add("sign", sign);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//
//        ResponseEntity<String> resEntity = restTemplate.exchange(mallUrl, HttpMethod.POST, request, String.class);
//        String response = resEntity.getBody();
//
//        return response;
//    }
//}

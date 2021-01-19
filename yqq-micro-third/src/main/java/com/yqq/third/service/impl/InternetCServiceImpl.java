/*
 * 文 件 名:  InternetCServiceImpl.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2020年3月11日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yqq.commons.service.BaseService;
import com.yqq.framework.utils.DateTimeUtils;
import com.yqq.framework.web.result.JSONResult;
import com.yqq.third.model.UserInfoBean;
import com.yqq.third.service.InternetCService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * 互联网中心接口实现
 * 
 * @author yangchuan
 * @see [相关类/方法]
 */
@Service
public class InternetCServiceImpl extends BaseService implements InternetCService {
    /**
     * 查询接口私钥
     */
    private final static String QUERYORDERS_PRIVATEKEY = "3F6D64DCCE77A97D";
    /**
     * 权益预开通查询接口私钥
     */
    private final static String QUERYISOPEN_PRIVATEKEY = "103C54DF22658459";
    /**
     * 渠道号
     */
    private final static String CHANNEL_CODE = "70741";
    /**
     * 查询接口appid
     */
    private final static String QUERYORDERS_APIID = "304";
    /**
     * 权益预开通查询接口appid
     */
    private final static String QUERYISOPEN_APIID = "200063";
    
    @Value("${xw.internet.url}")
    String internetUrl;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONResult<Object> queryOrders(UserInfoBean userInfoBean, String channelNo, String salesId, String status) {
        JSONResult<Object> result = new JSONResult<>();
        String transactionId = UUID.randomUUID().toString();
        String reqTime = DateTimeUtils.getTodayChar17();
        try {
            JSONObject body = new JSONObject();
            if (StringUtils.isNotEmpty(channelNo)) {
                body.put("channelNo", channelNo);
            }
            else {
                if (userInfoBean == null) {
                    result.setErrorInfo("查询错误：用户信息不能为空");
                    return result;
                }
                if (StringUtils.isEmpty(userInfoBean.getMobile())) {
                    result.setErrorInfo("查询错误：用户手机号不能为空");
                    return result;
                }
                body.put("serverNum", userInfoBean.getMobile());
                if (StringUtils.isNotEmpty(status)) {
                    body.put("status", status);
                }
                if (StringUtils.isNotEmpty(salesId)) {
                    body.put("salesId", salesId);
                }
            }
            // 生成签名
            String digitalSign = this.getDigitalSign(transactionId, reqTime, body, QUERYORDERS_PRIVATEKEY);
            JSONObject head = new JSONObject();
            head.put("apiId", QUERYORDERS_APIID);
            head.put("channelCode", CHANNEL_CODE);
            head.put("transactionId", transactionId);
            head.put("reqTime", reqTime);
            head.put("sign", digitalSign);
            head.put("version", "1.0");
            JSONObject req1 = new JSONObject();
            req1.put("head", head);
            req1.put("body", body);
            JSONObject req = new JSONObject();
            req.put("contractRoot", req1);
            logger.info("请求信息 {}", req.toJSONString());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json; charset=UTF-8");
            headers.add("Content-Type", "application/json");
            HttpEntity<JSONObject> requestEntity = new HttpEntity<>(req, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    internetUrl + "/cmpp-api/external/v3/queryOrders", HttpMethod.POST, requestEntity, String.class);
            String resBody = responseEntity.getBody();

            ilogger.info("internet\u0001queryOrders\u0001"+ userInfoBean.getMobile() + "\u0001" + req.toJSONString()  + "\u0001" + resBody);
            
            JSONObject allJson = JSONObject.parseObject(resBody);
            logger.info("返回信息 {}", allJson.toJSONString());
            JSONObject rootJson = JSONObject.parseObject(allJson.getString("contractRoot"));
            JSONObject bodyJson = JSONObject.parseObject(rootJson.getString("body"));
            if ("0000".equals(bodyJson.getString("resultCode"))) {
                result.setSuccessInfo(bodyJson, "查询成功");
            } else {
                result.setErrorInfo("查询错误：" + bodyJson.getString("resultMsg"));
            }
            return result;
        }
        catch (Exception e) {
            logger.info("订单查询 失败，用户号码[" + userInfoBean.getMobile() + "] exception{}", e);
            result.setFailInfo("订单查询 失败");
            return result;
        }
    }

    /**
     * 
     * 根据参数生成数字签名
     * 
     * @param transactionId 业务流水id
     * @param saleID        业务编码
     * @return
     */
    private String getDigitalSign(String transactionId, String reqTime, JSONObject body ,String privateKey) {
        // 签名格式 transactionId + reqTime + PRIVATEKEY+【body内容】
        StringBuffer sb = new StringBuffer();
        sb.append(transactionId);
        sb.append(reqTime);
        sb.append(privateKey);
        sb.append(body.toJSONString());
        String signParam = sb.toString();
        logger.info("数字签名 {}", signParam);
        String sign = DigestUtils.md5DigestAsHex(signParam.getBytes()).toUpperCase();
        return sign;
    }

    @Override
    public JSONResult<Object> queryIsOpenRights(UserInfoBean userInfoBean, String salesId, String prodId) {
        JSONResult<Object> result = new JSONResult<>();
        String transactionId = UUID.randomUUID().toString();
        String reqTime = DateTimeUtils.getTodayChar17();
        try {
            JSONObject body = new JSONObject();
            if (userInfoBean == null) {
                result.setErrorInfo("查询错误：用户信息不能为空");
                return result;
            }
            if (StringUtils.isEmpty(salesId)) {
                result.setErrorInfo("查询错误：业务编码不能为空");
                return result;
            }
            if (StringUtils.isEmpty(prodId)) {
                result.setErrorInfo("查询错误：产品编码不能为空");
                return result;
            }
            body.put("channelCode", CHANNEL_CODE);
            body.put("phone", userInfoBean.getMobile());
            body.put("salesId", salesId);
            JSONObject prod = new JSONObject();
            prod.put("prodId", prodId);
            body.put("prodInfo", prod);
            // 生成签名
            String digitalSign = this.getDigitalSign(transactionId, reqTime, body, QUERYISOPEN_PRIVATEKEY);
            JSONObject head = new JSONObject();
            head.put("apiId", QUERYISOPEN_APIID);
            head.put("channelCode", CHANNEL_CODE);
            head.put("transactionId", transactionId);
            head.put("reqTime", reqTime);
            head.put("sign", digitalSign);
            head.put("version", "1.0");
            JSONObject req1 = new JSONObject();
            req1.put("head", head);
            req1.put("body", body);
            JSONObject req = new JSONObject();
            req.put("contractRoot", req1);
            logger.info("请求信息 {}", req.toJSONString());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json; charset=UTF-8");
            headers.add("Content-Type", "application/json");
            HttpEntity<JSONObject> requestEntity = new HttpEntity<>(req, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    internetUrl + "/cmpp-api/external/queryIsOpenRights", HttpMethod.POST, requestEntity, String.class);
            String resBody = responseEntity.getBody();
            
            ilogger.info("internet\u0001queryIsOpenRights\u0001"+ userInfoBean.getMobile() + "\u0001" + req.toJSONString()  + "\u0001" + resBody);
            
            JSONObject allJson = JSONObject.parseObject(resBody);
            logger.info("返回信息 {}", allJson.toJSONString());
            JSONObject rootJson = JSONObject.parseObject(allJson.getString("contractRoot"));
            JSONObject bodyJson = JSONObject.parseObject(rootJson.getString("body"));
            if ("0000".equals(bodyJson.getString("resultCode"))) {
                JSONArray prodInfos = bodyJson.getJSONArray("prodInfo");
                JSONObject prodInfo = (JSONObject) prodInfos.get(0);
                // queryProdResultCode 0表示可以开通，1表示不能开通， 2表示重复开通，3表示业务互斥
                result.setSuccessInfo(prodInfo, "查询成功");
            }
            else {
                result.setErrorInfo("查询错误：" + bodyJson.getString("resultMsg"));
            }
            return result;
        }
        catch (Exception e) {
            logger.info("订单查询 失败，用户号码[" + userInfoBean.getMobile() + "]", e);
            result.setFailInfo("订单查询 失败");
            return result;
        }
    }
}

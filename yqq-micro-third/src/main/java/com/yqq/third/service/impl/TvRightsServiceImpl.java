///*
// * 文 件 名:  OpenTvRightsServiceImpl.java
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
//import com.yqq.framework.utils.DateTimeUtils;
//import com.yqq.third.enums.OpenRightsTypeEnum;
//import com.yqq.third.enums.TvMemberTypeEnum;
//import com.yqq.third.model.RightsParam;
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.model.request.wap.RightsLeadReq;
//import com.yqq.third.service.OpenRightsService;
//import com.yqq.third.utils.Base64;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URLEncoder;
//import java.security.*;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.List;
//
///**
// * 开通视频权益
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Deprecated
//@Service
//public class TvRightsServiceImpl extends BaseService implements OpenRightsService {
//    @Value("${xw.tv.url}")
//    String shuyeUrl;
//    private final static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIKV7+UxH5voKK3kiDyv8i8aV6dsr2u81PRMh9GuAH+NGE5ZDxkdM+YW+v2pUHmI0vfyxBLicnzDiw+K6AW+eJZGWySydXq7LGFAsvBDbjF6u/r9iyRlzfTrxtWTS6thuueXi/qkZMZVVkzzEPNsNuIpFPBt5FOouo2O90zyWUdlAgMBAAECgYA4tb6pzOyKO4c3BXE40bweQpVgay2/XxEoSvgM0kD7I54zoC+TW4BHYyF6+YWuUgI0laQbA2Zu0kjY5h0fKXmaQGVjfITWidwZbCH9MjZ0gvT4PycaKMy5pNcN+28yPFbFVqtvz9mskw8kjwCRe674QBuCIYQFH9af6B0ZVysYXQJBANc5nSf1pYh7jgHha08SbtVdk32TxRwRsfGMRh/p64Y3ZEPfOSZvu+5m1dQlN766HsCNJm6kd9zGgw12pcyoivsCQQCbU1NWEy89As9W7gC4fg9SmDiHMehHeEHZGEGEAvTiw3ESkFGs7ranfADB4wfjWAwacvR89XvuiAsOkUDPtukfAkBRQ160XMrgqo4m5Z8n3vTcORXcX3QYMhI2+tjrad5U8gs4mUsx2RRjYo6wKFFRAWNlHqZ5Nj5PrOCEr9zQVn8LAkBvv10feiHsNQ9SrVybXyZK9/UDXyaL9Lr7I3ZPMYlZc97vyiDY181VZeNtmpbcO0ZJ6RZ4qGyHSh0aV+pXBnNlAkEAikap4eiVKVLwHoXUKMM+RQ8x4KH41s4D80bTHT1v0PbMWdvaACpzQ/lbCO9+7ZpPilfWNJFbpjbWWjQMBFKRVA==";
//    private final static String ALGORITHM = "RSA";
//    private final static String SIGNATURE_ALGORITHM = "SHA1WithRSA";
//    @Autowired
//    private RestTemplate restTemplate;
//
//    /**
//     *
//     * 视频权益
//     * 测试数据  "YDZXHY_10Y","10"
//     * @param userInfoBean
//     * @param orderNum
//     * @param rightsParams  memberId 会员ID memberType 视频类型 qq 腾讯专用
//     * @return
//     */
//    @Override
//    public RightsHandleResult openRights(RightsLeadReq rightsLeadReq, List<RightsParam> rightsParams) {
//        RightsHandleResult jsonResult = new RightsHandleResult();
//        String memberId = "";
//        String memberType = "";
//        UserInfoBean userInfoBean = rightsLeadReq.getUserInfoBean();
//        String orderNum = rightsLeadReq.getDrawNum();
//        jsonResult.setDrawNum(orderNum);
//        String qq = userInfoBean.getQQ();
//        //TODO 视频权益 参数转换
//        for(RightsParam param : rightsParams) {
//           String paramNum = param.getParamNum();
//            param.getParamValue();
//            switch (paramNum) {
//                case "":
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        if (StringUtils.isEmpty(memberId)) {
//            logger.error("开通视频权益参数错误 memberId====== {}", memberId);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通视频权益参数错误 memberId" + memberId);
//            return jsonResult;
//
//        }
//        if (StringUtils.isEmpty(memberType)) {
//            logger.error("开通视频权益参数错误 memberType====== {}", memberType);
//            jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//            jsonResult.setHandleMsg("开通视频权益参数错误 memberType" + memberType);
//            return jsonResult;
//        }
//        try {
//            JSONObject jsonObject1 = new JSONObject();
//            jsonObject1.put("transactionId", orderNum);// 流水号
//            jsonObject1.put("transactionTime", DateTimeUtils.getTodayChar14());// 时间
//            jsonObject1.put("system", "1000");
//            jsonObject1.put("messageType", "1005");
//            JSONObject jsonObject2 = new JSONObject();
//            jsonObject2.put("msisdn", userInfoBean.getMobile());// 手机号
//            jsonObject2.put("spId", "910430");// 权益包企业代码
//            if (memberId.equals("YDZXHY_10Y")) {
//                memberId = TvMemberTypeEnum.YDZXHY_10Y.geNumVal();
//            }
//            if (memberId.equals("YDZXHY_20Y")) {
//                memberId = TvMemberTypeEnum.YDZXHY_20Y.geNumVal();
//            }
//            jsonObject2.put("spServiceId", memberId);// 会员编码10元:ZXHY10,20元:ZXHY20
//            jsonObject2.put("memberType", memberType);// 视频类型
//            // 腾讯专用
//            if (StringUtils.isNotEmpty(qq)) {
//                JSONObject jsonObject3 = new JSONObject();
//                jsonObject3.put("qq", qq);
//                jsonObject2.put("tx", jsonObject3);
//            }
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("public", jsonObject1);
//            jsonObject.put("busiInfo", jsonObject2);
//            String sings = sign(jsonObject.toJSONString().getBytes());
//
//            logger.info("请求json================{}",jsonObject.toJSONString());
//            logger.info("签名================{}",sings);
//            String url = shuyeUrl + "/open?sign=" + URLEncoder.encode(sings, "UTF-8");
//
//            logger.info("url================{}",url);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Accept", "application/json; charset=UTF-8");
//            headers.add("Content-Type", "application/json");
//
//            HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject, headers);
//
//            // "{\"result\":{\"code\":\"0000\",\"transactionTime\":\"20200108194515\",\"message\":\"成功\",\"transactionId\":\"1234\"}}";
//
//            ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//            String response = res.getBody();
//
//            JSONObject responseJson = JSONObject.parseObject(response);
//            String result = responseJson.getString("result");
//            JSONObject jsondata = JSONObject.parseObject(result);
//            String code = jsondata.getString("code");
//            if ("0000".equals(code)) {
//                logger.info("开通视频权益成功");
//                jsonResult.setHandleStatus(RightsHandleResult.SUCCESS);
//                jsonResult.setHandleMsg("开通视频权益成功");
//            } else {
//                logger.error("{}========================== 开通视频权益失败  code{} message {}", userInfoBean.getMobile(), code, jsondata.getString("message"));
//                jsonResult.setHandleStatus(RightsHandleResult.FAIL);
//                jsonResult.setHandleMsg("开通视频权益失败 "+jsondata.getString("message"));
//            }
//            return jsonResult;
//        } catch (Exception e) {
//            logger.error("@@@@@@Error-OpenTvRightsService: " + "transactionId=" + orderNum + "@" + "phone=" + userInfoBean.getMobile() + "@" + "memberId=" + memberId + "@"
//                    + "memberType=" + memberType + "@" + "qq=" + qq);
//            jsonResult.setHandleStatus(RightsHandleResult.EXCEPT);
//            jsonResult.setHandleMsg(e.getMessage());
//            return jsonResult;
//        }
//    }
//
//    /**
//     *
//     * 视频会员签名
//     * @param json
//     * @return
//     * @throws InvalidKeySpecException
//     * @throws NoSuchAlgorithmException
//     * @throws SignatureException
//     * @throws InvalidKeyException
//     */
//    private String sign(byte[] json) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
//        PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
//        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
//        PrivateKey key = factory.generatePrivate(pkcs);
//        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//        signature.initSign(key);
//        signature.update(json);
//        return Base64.encode(signature.sign());
//    }
//
//
//    //签名校验
//    public boolean verify(byte[] json, String publicKey, String sign) {
//        try {
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
//            KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
//            PublicKey key = factory.generatePublic(keySpec);
//            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//            signature.initVerify(key);
//            signature.update(json);
//            return signature.verify(Base64.decode(sign));
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    @Override
//    public OpenRightsTypeEnum getTypeEnum() {
//        return OpenRightsTypeEnum.R_TV;
//    }
//}

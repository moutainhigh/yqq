/*
 * 文 件 名:  MtRightsServiceImpl.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  lib 工号
 * 修改时间:  2020年4月13日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yqq.commons.model.RightsHandleResult;
import com.yqq.commons.service.BaseService;
import com.yqq.third.enums.OpenRightsTypeEnum;
import com.yqq.third.model.RightsParam;
import com.yqq.third.model.UserInfoBean;
import com.yqq.third.model.request.wap.RightsLeadReq;
import com.yqq.third.service.OpenRightsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * <功能描述>
 * 
 * @author  lib 工号
 * @see  [相关类/方法]
 */
@Service
public class MtRightsServiceImpl extends BaseService implements OpenRightsService {
    
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";
    private static final String IV_SPEC = "1513D520B9C1459C";
    
    @Value("${xw.mt.url}")
    String mtUrl;
    @Value("${xw.mt.appid}")
    String appid;
    @Value("${xw.mt.token}")
    String token;
    @Value("${xw.mt.channelUrlKey}")
    String channel_url_key;
    @Value("${xw.mt.aesKey}")
    String AES_KEY;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 重载方法
     * @param userInfoBean
     * @param orderNum
     * @param rightsParams
     * @return
     */
    @Override
    public RightsHandleResult openRights(RightsLeadReq rightsLeadReq, List<RightsParam> rightsParams) {
        RightsHandleResult jsonResult = new RightsHandleResult();
        String drawNum = rightsLeadReq.getDrawNum();
        jsonResult.setDrawNum(drawNum);
        UserInfoBean userInfoBean = rightsLeadReq.getUserInfoBean();
        String mobile = userInfoBean.getMobile();
        try {
           mobile = encrypt(mobile, AES_KEY);
        } catch (Exception e) {
            logger.error("Exception : MeiTuanUtil.getMeiTuanUrl.encrypt({},AES_KEY) exception{}", mobile, e);
            jsonResult.setHandleStatus(RightsHandleResult.EXCEPT);
            jsonResult.setHandleMsg("开通美团权益参数错误  mobile 加密错误" + e.getMessage());
            return jsonResult;
        }
        logger.info(" mobile encrypt = {} ", mobile);
        List<String> paras = new ArrayList<String>();
        paras.add("channel_url_key=" + channel_url_key);
        paras.add("phone=" + mobile);
        paras.add("user_real_ip=" + (StringUtils.isNotEmpty(userInfoBean.getRemoteAddr())? userInfoBean.getRemoteAddr() : ""));
        paras.add("longitude=");
        paras.add("latitude=");
        paras.add("unique_mark=" + drawNum);
        String requestUrl = "";
        try {
          HttpHeaders headers = new HttpHeaders();
          headers.add("Accept", "application/json; charset=UTF-8");
          HttpEntity<LinkedHashMap<String, Object>> requestEntity = new HttpEntity<>(headers);
          requestUrl = this.getSignUrl(paras);
          URI uri = new URI(requestUrl);
          ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
          String response = responseEntity.getBody();
          
          logger.info("美团请求返回信息 response =========== {} ", response);
          ilogger.info("mt\u0001mt\u0001"+ userInfoBean.getMobile() + "\u0001" + requestUrl + "\u0001" + response);
          JSONObject rObject =  JSONObject.parseObject(response);
          String code = rObject.getString("code");
          //办理成功
          if("1".equals(code)) {
              jsonResult.setHandleStatus(RightsHandleResult.SUCCESS);
              jsonResult.setHandleMsg("开通成功");
          } else {
              jsonResult.setHandleStatus(RightsHandleResult.FAIL);
              String msg = rObject.getString("msg");
              jsonResult.setHandleMsg(msg + code);
          }
          return jsonResult;
        } catch (Exception e) {
            logger.error("Exception :  开通权益错误  mobile mobile = {} {} ",mobile, e);
            ilogger.info("mt\u0001mt\u0001"+ userInfoBean.getMobile() + "\u0001" + requestUrl + "\u0001" + e.getMessage());
            jsonResult.setHandleStatus(RightsHandleResult.EXCEPT);
            jsonResult.setHandleMsg("开通权益错误 异常信息"+e.getMessage());
            return jsonResult;
        }
    }
    
    /**
     * 重载方法
     * @return
     */
    @Override
    public OpenRightsTypeEnum getTypeEnum() {
        return OpenRightsTypeEnum.R_MT;
    }
    
    /**
     * 生成访问地址
     *
     * @param base_url 基础地址，比如：http://thirdapi.waimai.test.meituan.com/api/coupon/v1/send
     * @param paras    接口的业务参数
     * @return
     */
    private String getSignUrl(List<String> paras) throws NoSuchAlgorithmException {
        paras.add("app_id=" + appid);
        paras.add("timestamp=" + (int) (System.currentTimeMillis() / 1000));
        Collections.sort(paras);

        StringBuffer urlQueryPartSB = new StringBuffer();
        urlQueryPartSB.append("?");
        for (String string : paras) {
            urlQueryPartSB.append((string));
            urlQueryPartSB.append("&");
        }
        urlQueryPartSB.deleteCharAt(urlQueryPartSB.length() - 1);
        String queryPart = urlQueryPartSB.toString();
        urlQueryPartSB.insert(0, mtUrl);
        urlQueryPartSB.append(token);

        MessageDigest md = MessageDigest.getInstance("MD5");
        String sign = byte2hex(md.digest(urlQueryPartSB.toString().getBytes()));
        return mtUrl + convertBase64ToEscapedURL(queryPart) + "&sign=" + sign;
    }

    private String encrypt(String sSrc, String sKey) throws Exception {
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(ALGORITHM);// "算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec(IV_SPEC.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        
        return Base64.getEncoder().encodeToString(encrypted);// 此处使用BAES64做转码功能，同时能起到2次加密的作用。
    }

    private String byte2hex(byte[] b) {
        StringBuffer buf = new StringBuffer();
        int i;

        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }

    /**
     * 将Base64中特殊字符 '+' '/' 进行转码
     */
    private String convertBase64ToEscapedURL(String base64) {
        return base64.replace("+", "%2B").replace("/", "%2F");
    }
    
}

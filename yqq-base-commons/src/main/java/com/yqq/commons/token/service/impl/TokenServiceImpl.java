/*
 * 文 件 名:  TokenServiceImpl.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年1月31日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.commons.token.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.yqq.commons.cached.service.RedisService;
import com.yqq.commons.model.Token;
import com.yqq.commons.token.service.TokenService;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Service
public class TokenServiceImpl implements TokenService {

    private Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    private final static String TOKEN_PREFIX = "yqq_token:";
    
    @Override
    public Token getToken(String tokenUrl, String grantType, String clientId, String clientSecret) {
     // 封装头信息
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", grantType);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);

        MultiValueMap<String, Object>  headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json; charset=UTF-8");
        headers.add("Content-Type", "application/json");

        // token值
        Token token = new Token();
        // 有效时长
        Long expireAt = 60 * 1000L;
        try {
            // 先取缓存 缓存有数据则取缓存 无数据则重新调用接口查询
            if (redisService.isStringExits(TOKEN_PREFIX + clientId)) {
                return (Token) redisService.getStringKey(TOKEN_PREFIX + clientId);
            }
            synchronized (expireAt) {
                // 获取数据
                HttpEntity<Map> request = new HttpEntity(params, headers);
                ResponseEntity<Token> responseEntity = restTemplate.exchange(
                        tokenUrl + "&client_id={client_id}&client_secret={client_secret}&format=json", HttpMethod.POST, request,
                        Token.class, params);
                if (null == responseEntity) {
                    throw new Exception("获取Token接口异常！");
                }
                token = responseEntity.getBody();
                logger.info("token:{},expireAt:{}", token, expireAt);

                // 在有效时长范围内token存入缓存
                redisService.updateStringKey(TOKEN_PREFIX + clientId, token, token.getExpires_in()/2);
            }

        } catch (Exception e) {
            logger.error("getToken :{},{}", e.getMessage(), e);
        }
        return token;
    }
}

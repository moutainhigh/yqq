/*
 * 文 件 名:  FeignConfigInterceptor.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年1月29日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.commons.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.yqq.commons.model.Token;
import com.yqq.commons.token.service.TokenService;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * token 拦截器
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Configuration
public class TokenInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    private static final String GRANT_TYPE = "client_credentials";

    private final static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
    
    @Autowired
    private TokenService tokenService;

    @Value("${xw.token.appId}")
    String appId;
    
    @Value("${xw.token.appSecret}")
    String appSecret;

    @Value("${xw.token.url}")
    String tokenUrl;

    @Value("${xw.token.gateWaySwitch}")
    boolean gateWaySwitch;
    
    /**
     * 重载方法
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        template.header("Accept", "application/json; charset=UTF-8");
        template.header("Content-Type", "application/json");
        logger.info("gateWay Switch "+ gateWaySwitch);
        if(gateWaySwitch) {
            Token token = tokenService.getToken(tokenUrl,GRANT_TYPE, appId, appSecret);
            logger.info(token.getAccess_token());
            template.header(AUTHORIZATION_HEADER, String.format("%s %s",BEARER_TOKEN_TYPE,token.getAccess_token()));
        }
        
    } 
    
}

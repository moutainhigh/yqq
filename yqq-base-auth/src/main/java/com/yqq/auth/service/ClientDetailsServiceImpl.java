/*
 * 文 件 名:  ClientDetailsServiceImpl.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年2月15日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.auth.service;

import com.yqq.auth.dao.ClientDetailsMapper;
import com.yqq.auth.model.ClientDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Service("clientDetailsServiceImpl")
public class ClientDetailsServiceImpl implements ClientDetailsService {
    
    @Autowired
    private ClientDetailsMapper clientDetailsMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    private final static String CLIENT_DETAIL_PREFIX = "xw_client_detail:";
    
    private final Long expire = 86400L;

    private final static Logger logger = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);
    /**
     * 重载方法
     * @param clientId
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId){
        ClientDetail clientDetail = new ClientDetail();
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(CLIENT_DETAIL_PREFIX + clientId)){
            clientDetail = (ClientDetail) operations.get(CLIENT_DETAIL_PREFIX + clientId);
        }else {
            clientDetail = clientDetailsMapper.getClientDetailByAppId(clientId);
            operations.set(CLIENT_DETAIL_PREFIX + clientId, clientDetail, expire, TimeUnit.SECONDS);
        }
        
        logger.info("get clientDetail --------------------------------"+ clientDetail.toString());
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(clientId);
        baseClientDetails.setClientSecret("{bcrypt}" + new BCryptPasswordEncoder().encode(clientDetail.getAppSecret()));
        List<String> scope = Arrays.asList(clientDetail.getScope().split(","));
        baseClientDetails.setScope(scope);
        List<String> grantTypes = Arrays.asList(clientDetail.getGrantTypes().split(","));
        baseClientDetails.setAuthorizedGrantTypes(grantTypes);
        baseClientDetails.setAccessTokenValiditySeconds(clientDetail.getAccessTokenValidity());
        baseClientDetails.setRefreshTokenValiditySeconds(clientDetail.getRefreshTokenValidity());
        return baseClientDetails;
    }
}

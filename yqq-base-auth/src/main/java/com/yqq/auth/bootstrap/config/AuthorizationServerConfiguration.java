/*
 * 文 件 名:  AuthorizationServerConfiguration.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年1月31日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.auth.bootstrap.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
    private final static Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);
    
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 保存令牌数据栈
     */
    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
    @Qualifier("clientDetailsServiceImpl")
    private ClientDetailsService clientDetailsService;
    

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory); 
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("Xwtec190219");
        return converter;
    }

    /**
     * 
     * 配置 ClientDetailsService 独立client客户端的信息。包括权限范围、授权方式、客户端权限等配置。
     * 授权方式有4种:implicit, client_redentials, password , authorization_code, 其中密码授权方式必须结合 AuthenticationManager 进行配置。
     * 必须至少配置一个客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }
    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;
    /**
     * 
     * 配置AuthorizationServer 端点的非安全属性，也就是 token 存储方式、token 配置、用户授权模式等。
     * 默认不需做任何配置，除非使用 密码授权方式, 这时候必须配置 AuthenticationManager
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        
        endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter()).reuseRefreshTokens(true)
                .authenticationManager(authenticationManager).exceptionTranslator(webResponseExceptionTranslator);
    }
    /**
     * 
     * 声明安全约束，哪些允许访问，哪些不允许访问。配置 AuthorizationServer 的安全属性，也就是endpoint /oauth/token 。
     * /oauth/authorize 则和其它用户 REST 一样保护。可以不配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}

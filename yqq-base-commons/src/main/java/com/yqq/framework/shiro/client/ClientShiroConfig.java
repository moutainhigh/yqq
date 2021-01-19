package com.yqq.framework.shiro.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;

import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.yqq.framework.shiro.ShiroConfig;
import com.yqq.framework.shiro.filters.CustomRolesAuthorizationFilter;
import com.yqq.framework.shiro.properties.ClientProperties;

/**
 * 
 * 客户端 shiro 配置文件加载
 * 
 * @author  libiao 工号
 * @see  [相关类/方法]
 */
@Import({ShiroConfig.class})
@Configuration
@EnableConfigurationProperties(value = {ClientProperties.class})
@EnableAutoConfiguration
public class ClientShiroConfig {
    
    /**
     * 由于在remoteService中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
     */
    @Autowired 
    private RestTemplateBuilder builder; 

    @Bean 
    public RestTemplate restTemplate() { 
        return builder.build(); 
    } 
    
    @Bean
    public com.yqq.framework.shiro.client.RemoteService getRemoteService(ClientProperties properties) {
        com.yqq.framework.shiro.client.RemoteService remoteService = new com.yqq.framework.shiro.client.RemoteServiceImpl(properties.getGlobalAuthUrl());
        return remoteService;
    }

    @Bean(name = "clientRealm", initMethod = "init")
    public ClientRealm getClientRealm(com.yqq.framework.shiro.client.RemoteService remoteService, ClientProperties properties) {
        ClientRealm clientRealm = new ClientRealm();
        clientRealm.setAppKey(properties.getAppKey());
        clientRealm.setCachingEnabled(false);
        clientRealm.setRemoteService(remoteService);
        return clientRealm;
    }
    
    /**
     * @return
     */
    @Bean(name = "sessionDao")
    public ClientSessionDAO getClientSessionDAO(com.yqq.framework.shiro.client.RemoteService remoteService,
                                                ClientProperties properties) {
        ClientSessionDAO clientSessionDAO = new ClientSessionDAO();
        clientSessionDAO.setSessionIdGenerator(new SessionIdGenerator() {
            @Override
            public Serializable generateId(Session session) {
                return UUID.randomUUID().toString().replaceAll("-", "");
            }
        });
        clientSessionDAO.setAppKey(properties.getAppKey());
        clientSessionDAO.setRemoteService(remoteService);
        return clientSessionDAO;
    }

    /**
     * 会话管理器
     *
     * @return
     */
    @Bean(name = "sessionManager", destroyMethod = "destroy")
    public DefaultWebSessionManager getDefaultWebSessionManager(ClientSessionDAO sessionDao, Cookie sessionIdCookie) {
        DefaultWebSessionManager defaultWebSessionManager = new com.yqq.framework.shiro.client.CustomDefaultWebSessionManager();
        defaultWebSessionManager.setDeleteInvalidSessions(false);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(false);
        defaultWebSessionManager.setSessionDAO(sessionDao);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        defaultWebSessionManager.setSessionIdCookie(sessionIdCookie);
        return defaultWebSessionManager;
    }

    /**
     * 安全管理器
     *
     * @param clientRealm
     * @param sessionManager
     * @param rememberMeManager
     * @return
     */
    @Bean(name = "securityManager", destroyMethod = "destroy")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(ClientRealm clientRealm,
            DefaultWebSessionManager sessionManager, RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(clientRealm);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager);
        return defaultWebSecurityManager;
    }

    @Bean(name = "clientAuthenticationFilter")
    public ClientAuthenticationFilter getClientAuthenticationFilter() {
        return new ClientAuthenticationFilter();
    }

    /**
     * 配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     *
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
            ClientAuthenticationFilter clientAuthenticationFilter, ClientProperties properties ) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(properties.getLoginUrl());
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl(properties.getSuccessUrl());
        // 登录不成功
        shiroFilterFactoryBean.setUnauthorizedUrl(properties.getUnauthorizedUrl());
        Map<String, Filter> filterMap = new HashMap<>(2);
       // filterMap.put("authc", clientAuthenticationFilter);
        filterMap.put("roles", new CustomRolesAuthorizationFilter(properties.getAppKey()));

        //可以在此处添加filter
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        filterChainDefinitionMap.putAll(properties.getDefinitions());
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注册DelegatingFilterProxy（Shiro）
     * 集成Shiro有2种方法：
     * 1. 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
     * 在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了）
     * 2. 直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
     * 默认拦截 /*）
     *
     * @return
     * @author SHANHY
     * @create 2016年1月13日
     */
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean(
                new DelegatingFilterProxy("shiroFilter"));
        // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        // 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

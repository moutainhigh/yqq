package com.yqq.framework.shiro.properties;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * shiro 客户端配置文件
 * 
 * @author  libiao 工号
 * @see  [相关类/方法]
 */
@Component
@ConfigurationProperties(prefix = "shiro.client")
public class ClientProperties {

    private String platformKey;

    /**
     * 应用标识
     */
    private String appKey;

    /**
     * 登录地址
     */
    private String loginUrl = "/login";


    /**
     * 登录成功默认跳转地址
     */
    private String successUrl = "/";


    /**
     * 未授权跳转地址
     */
    private String unauthorizedUrl = "/unauthorized";



    /**
     * 远程权限验证路径
     */
    private  String globalAuthUrl;

    /**
     * 密码加密算法 MD5
     */
    private String hashAlgorithmName = "md5";
    
    /**
     * 密码加密次数
     */
    private int hashIterations = 2;
    
    /**
     * shiro 过滤器匹配规则
     */
    private Map<String,String> definitions = new LinkedHashMap<>();


    public String getPlatformKey()
    {
        return platformKey;
    }


    public void setPlatformKey(String platformKey)
    {
        this.platformKey = platformKey;
    }


    public String getLoginUrl()
    {
        return loginUrl;
    }


    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }


    public String getSuccessUrl()
    {
        return successUrl;
    }


    public void setSuccessUrl(String successUrl)
    {
        this.successUrl = successUrl;
    }


    public String getUnauthorizedUrl()
    {
        return unauthorizedUrl;
    }


    public void setUnauthorizedUrl(String unauthorizedUrl)
    {
        this.unauthorizedUrl = unauthorizedUrl;
    }


    public String getGlobalAuthUrl()
    {
        return globalAuthUrl;
    }


    public void setGlobalAuthUrl(String globalAuthUrl)
    {
        this.globalAuthUrl = globalAuthUrl;
    }


    public Map<String, String> getDefinitions()
    {
        return definitions;
    }


    public void setDefinitions(Map<String, String> definitions)
    {
        this.definitions = definitions;
    }


    public String getAppKey()
    {
        return appKey;
    }


    public void setAppKey(String appKey)
    {
        this.appKey = appKey;
    }


    /**
     * 获取 hashAlgorithmName
     * @return 返回 hashAlgorithmName
     */
    public String getHashAlgorithmName() {
        return hashAlgorithmName;
    }


    /**
     * 设置 hashAlgorithmName
     * @param hashAlgorithmName 对hashAlgorithmName进行赋值
     */
    public void setHashAlgorithmName(String hashAlgorithmName) {
        this.hashAlgorithmName = hashAlgorithmName;
    }


    /**
     * 获取 hashIterations
     * @return 返回 hashIterations
     */
    public int getHashIterations() {
        return hashIterations;
    }


    /**
     * 设置 hashIterations
     * @param hashIterations 对hashIterations进行赋值
     */
    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

}

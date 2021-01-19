/*
 * 文 件 名:  ClientDetail.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年2月19日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.auth.model;

import java.io.Serializable;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class ClientDetail implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8603437048068836597L;
    
    private String appId;
    
    private String appSecret;
    
    private String scope;
    

    private String grantTypes;
    
    private Integer accessTokenValidity;
    
    private Integer refreshTokenValidity;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    @Override
    public String toString() {
        return "ClientDetail [ appId= " + appId +",appSecret= " + appSecret
                +",scope= " + scope
                +",grantTypes= " + grantTypes
                +",accessTokenValidity= " + accessTokenValidity
                +",refreshTokenValidity= " + refreshTokenValidity
                +"]" ;
    }
}

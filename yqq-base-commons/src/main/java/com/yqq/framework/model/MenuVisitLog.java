/*
 * 文 件 名:  MenuVisitLog.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wangshaokun 4674
 * 修改时间:  2017年8月31日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.model;

import java.io.Serializable;

/**
 * <功能描述>
 * 
 * @author wangshaokun 4674
 * @see  [相关类/方法]
 */
public class MenuVisitLog implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -800735613751340171L;
    
    /**
     * 日志Id
     */
    private String visitId;
    
    /**
     * 资源编码
     */
    private String resourceNum;
    
    /**
     * 资源名称
     */
    private String resourceName;
    
    /**
     * 资源访问地址
     */
    private String resourceUrl;
    
    /**
     * 资源访问时间
     */
    private String visitTime;
    
    /**
     * 访问人编码
     */
    private String loginName;
    
    /**
     * 访问人名称
     */
    private String cnName;

    
    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(String resourceNum) {
        this.resourceNum = resourceNum;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
    
}

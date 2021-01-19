/*
 * 文 件 名:  TemplateIdMap.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2018年11月8日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.model;

import java.io.Serializable;

/**
 * <短信模板ID Map>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class TemplateIdMap implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5681894334928664334L;

    /**
     * 短信模板ID
     */
    private String smsTemplateId;
    
    /**
     * 站内信模板ID
     */
    private String msgTemplateId;
    
    /**
     * 微信公众号模板ID
     */
    private String wxgzhTemplateId;
    
    /**
     * 微信小程序模板ID
     */
    private String wxxcxTemplateId;
    
    /**
     * APP推送模板ID
     */
    private String appTemplateId;

    /**
     * 获取 smsTemplateId
     * @return 返回 smsTemplateId
     */
    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    /**
     * 设置 smsTemplateId
     * @param smsTemplateId 对smsTemplateId进行赋值
     */
    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    /**
     * 获取 msgTemplateId
     * @return 返回 msgTemplateId
     */
    public String getMsgTemplateId() {
        return msgTemplateId;
    }

    /**
     * 设置 msgTemplateId
     * @param msgTemplateId 对msgTemplateId进行赋值
     */
    public void setMsgTemplateId(String msgTemplateId) {
        this.msgTemplateId = msgTemplateId;
    }

    /**
     * 获取 wxgzhTemplateId
     * @return 返回 wxgzhTemplateId
     */
    public String getWxgzhTemplateId() {
        return wxgzhTemplateId;
    }

    /**
     * 设置 wxgzhTemplateId
     * @param wxgzhTemplateId 对wxgzhTemplateId进行赋值
     */
    public void setWxgzhTemplateId(String wxgzhTemplateId) {
        this.wxgzhTemplateId = wxgzhTemplateId;
    }

    /**
     * 获取 wxxcxTemplateId
     * @return 返回 wxxcxTemplateId
     */
    public String getWxxcxTemplateId() {
        return wxxcxTemplateId;
    }

    /**
     * 设置 wxxcxTemplateId
     * @param wxxcxTemplateId 对wxxcxTemplateId进行赋值
     */
    public void setWxxcxTemplateId(String wxxcxTemplateId) {
        this.wxxcxTemplateId = wxxcxTemplateId;
    }

    /**
     * 获取 appTemplateId
     * @return 返回 appTemplateId
     */
    public String getAppTemplateId() {
        return appTemplateId;
    }

    /**
     * 设置 appTemplateId
     * @param appTemplateId 对appTemplateId进行赋值
     */
    public void setAppTemplateId(String appTemplateId) {
        this.appTemplateId = appTemplateId;
    }
    
}

/*
 * 文 件 名:  ReqSms.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2018年9月27日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.model;

import java.io.Serializable;


/**
 * <发送短信请求实体>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class ReqSms implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4752073662809671863L;
    
    /**
     * 手机号码
     */
    private String phoneNumbers;
    
    /**
     * 接收者ID
     */
    private String recIds;
    
    /**
     * 微信公众号openId
     */
    private String wxgzhOpenIds;
    
    /**
     * 微信小程序openId
     */
    private String wxxcxOpenIds;

    /**
     * 短信模板IDMap
     */
    private TemplateIdMap templateIdMap;
    
    /**
     * 短信模板参数
     */
    private String templateParam;

    /**
     * 站内信跳转URL参数
     */
    private String linkUrlParam;
    
    /**
     * 发送人ID
     */
    private String sendId;
    
    /**
     * 发送人姓名
     */
    private String sendName;

    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 真实租户ID
     */
    private String realTenantId;
    
    /**
     * 公众号 小程序颜色
     */
    private String color;
    
    /**
     * 模板需要放大的关键词，不填则默认无放大
     */
    private String emphasisKeyword;
    
    /**
     * 扩展字段1
     */
    private String extendField1;
    
    /**
     * 扩展字段2
     */
    private String extendField2;
    
	/**
	 * 获取 extendField1
	 * @return 返回 extendField1
	 */
	public String getExtendField1() {
		return extendField1;
	}

	/**
	 * 设置 extendField1
	 * @param extendField1 对extendField1进行赋值
	 */
	public void setExtendField1(String extendField1) {
		this.extendField1 = extendField1;
	}

	/**
	 * 获取 extendField2
	 * @return 返回 extendField2
	 */
	public String getExtendField2() {
		return extendField2;
	}

	/**
	 * 设置 extendField2
	 * @param extendField2 对extendField2进行赋值
	 */
	public void setExtendField2(String extendField2) {
		this.extendField2 = extendField2;
	}

    
    /**
     * 获取 emphasisKeyword
     * @return 返回 emphasisKeyword
     */
    public String getEmphasisKeyword() {
        return emphasisKeyword;
    }

    /**
     * 设置 emphasisKeyword
     * @param emphasisKeyword 对emphasisKeyword进行赋值
     */
    public void setEmphasisKeyword(String emphasisKeyword) {
        this.emphasisKeyword = emphasisKeyword;
    }

    /**
     * 获取 color
     * @return 返回 color
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置 color
     * @param color 对color进行赋值
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 小程序 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?id=0）。该字段不填则模板无跳转
     */
    private String page;
    
    /**
     * 小程序 表单提交场景下，为 submit 事件带上的form_Id；支付场景下，为本次支付的prepay_id
     */
    private String formId;
    
    /**
     * 获取 page
     * @return 返回 page
     */
    public String getPage() {
        return page;
    }

    /**
     * 设置 page
     * @param page 对page进行赋值
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * 获取 formId
     * @return 返回 formId
     */
    public String getFormId() {
        return formId;
    }

    /**
     * 设置 formId
     * @param formId 对formId进行赋值
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     * 获取 phoneNumbers
     * @return 返回 phoneNumbers
     */
    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * 设置 phoneNumbers
     * @param phoneNumbers 对phoneNumbers进行赋值
     */
    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * 获取 recIds
     * @return 返回 recIds
     */
    public String getRecIds() {
        return recIds;
    }

    /**
     * 设置 recIds
     * @param recIds 对recIds进行赋值
     */
    public void setRecIds(String recIds) {
        this.recIds = recIds;
    }

    /**
     * 获取 wxgzhOpenIds
     * @return 返回 wxgzhOpenIds
     */
    public String getWxgzhOpenIds() {
        return wxgzhOpenIds;
    }

    /**
     * 设置 wxgzhOpenIds
     * @param wxgzhOpenIds 对wxgzhOpenIds进行赋值
     */
    public void setWxgzhOpenIds(String wxgzhOpenIds) {
        this.wxgzhOpenIds = wxgzhOpenIds;
    }

    /**
     * 获取 wxxcxOpenIds
     * @return 返回 wxxcxOpenIds
     */
    public String getWxxcxOpenIds() {
        return wxxcxOpenIds;
    }

    /**
     * 设置 wxxcxOpenIds
     * @param wxxcxOpenIds 对wxxcxOpenIds进行赋值
     */
    public void setWxxcxOpenIds(String wxxcxOpenIds) {
        this.wxxcxOpenIds = wxxcxOpenIds;
    }

    /**
     * 获取 templateIdMap
     * @return 返回 templateIdMap
     */
    public TemplateIdMap getTemplateIdMap() {
        return templateIdMap;
    }

    /**
     * 设置 templateIdMap
     * @param templateIdMap 对templateIdMap进行赋值
     */
    public void setTemplateIdMap(TemplateIdMap templateIdMap) {
        this.templateIdMap = templateIdMap;
    }

    /**
     * 获取 templateParam
     * @return 返回 templateParam
     */
    public String getTemplateParam() {
        return templateParam;
    }

    /**
     * 设置 templateParam
     * @param templateParam 对templateParam进行赋值
     */
    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    /**
     * 获取 linkUrlParam
     * @return 返回 linkUrlParam
     */
    public String getLinkUrlParam() {
        return linkUrlParam;
    }

    /**
     * 设置 linkUrlParam
     * @param linkUrlParam 对linkUrlParam进行赋值
     */
    public void setLinkUrlParam(String linkUrlParam) {
        this.linkUrlParam = linkUrlParam;
    }

    /**
     * 获取 sendId
     * @return 返回 sendId
     */
    public String getSendId() {
        return sendId;
    }

    /**
     * 设置 sendId
     * @param sendId 对sendId进行赋值
     */
    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    /**
     * 获取 sendName
     * @return 返回 sendName
     */
    public String getSendName() {
        return sendName;
    }

    /**
     * 设置 sendName
     * @param sendName 对sendName进行赋值
     */
    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    /**
     * 获取 tenantId
     * @return 返回 tenantId
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置 tenantId
     * @param tenantId 对tenantId进行赋值
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 获取 realTenantId
     * @return 返回 realTenantId
     */
    public String getRealTenantId() {
        return realTenantId;
    }

    /**
     * 设置 realTenantId
     * @param realTenantId 对realTenantId进行赋值
     */
    public void setRealTenantId(String realTenantId) {
        this.realTenantId = realTenantId;
    }

}

/*
 * 文 件 名:  UserInfoBean.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2020年3月3日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.model;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class UserInfoBean implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6444908298852249226L;
    /**用户ID */
    private String userId;
    /** 登陆渠道 */
    private int loginSource;
    /** 登陆方式*/
    private String loginType;
    /** sso cookie token */
    private String ssoCookieToken;
    /** sso 报活时间 */
    private String ssoActiveTime;

    /** 用户当前状态 */
    private String userState;
    /** 用户手机号码 */
    private String mobile;
    /** 用户账号*/
    private String userAccount;
    /**用户类型*/
    private  String userType;

    public String getProviceNo() {
        return proviceNo;
    }

    public void setProviceNo(String proviceNo) {
        this.proviceNo = proviceNo;
    }

    /** 用户密码 */
    private String pwd;
    /** 所属省份编码*/
    private  String proviceNo;
    /** 用户所属地市编码 */
    private String cityNo;
    /** 用户所属县区编码 */
    private String countryNo;
    /** 用户手机品牌 */
    private String brandNum;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /** 用户开户时间 */
    private String applyDate;
    /** 用户名称 */
    private String userName;
    /** 用户昵称*/
    private String nickName;
    /** 用户付费模式 */
    private String userpayMode;
    /** 用户证件类型 */
    private String custIcType;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /** 用户证件号码 */
    private String custIcNo;
    /** 用户联系电话 */
    private String contactTel;
    /** 用户邮箱地址 */
    private String email;
    /** 用户邮政编码 */
    private String postCode;
    /** SESSIONID */
    private String sessionId;

    /**
     * 渠道号
     */
    private String chValue;
    /**
     * 用户IP
     */
    private String remoteAddr;

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    /**
     * 用户手机串号
     */
    private String imei;

    public int getAttentions() {
        return attentions;
    }

    public void setAttentions(int attentions) {
        this.attentions = attentions;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    /**注册时间*/
    private String registTime;
    /**出生日期*/
    private String brithday;
    /** 关注人数*/
    private int attentions;
    /** 粉丝数*/
    private int fans;

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    /**
     * 获取 loginSource
     * @return 返回 loginSource
     */
    public int getLoginSource() {
        return loginSource;
    }
    /**
     * 设置 loginSource
     * @param loginSource 对loginSource进行赋值
     */
    public void setLoginSource(int loginSource) {
        this.loginSource = loginSource;
    }
    /**
     * 获取 loginType
     * @return 返回 loginType
     */
    public String getLoginType() {
        return loginType;
    }
    /**
     * 设置 loginType
     * @param loginType 对loginType进行赋值
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
    /**
     * 获取 ssoCookieToken
     * @return 返回 ssoCookieToken
     */
    public String getSsoCookieToken() {
        return ssoCookieToken;
    }
    /**
     * 设置 ssoCookieToken
     * @param ssoCookieToken 对ssoCookieToken进行赋值
     */
    public void setSsoCookieToken(String ssoCookieToken) {
        this.ssoCookieToken = ssoCookieToken;
    }
    /**
     * 获取 ssoActiveTime
     * @return 返回 ssoActiveTime
     */
    public String getSsoActiveTime() {
        return ssoActiveTime;
    }
    /**
     * 设置 ssoActiveTime
     * @param ssoActiveTime 对ssoActiveTime进行赋值
     */
    public void setSsoActiveTime(String ssoActiveTime) {
        this.ssoActiveTime = ssoActiveTime;
    }
    /**
     * 获取 userState
     * @return 返回 userState
     */
    public String getUserState() {
        return userState;
    }
    /**
     * 设置 userState
     * @param userState 对userState进行赋值
     */
    public void setUserState(String userState) {
        this.userState = userState;
    }
    /**
     * 获取 mobile
     * @return 返回 mobile
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置 mobile
     * @param mobile 对mobile进行赋值
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * 获取 pwd
     * @return 返回 pwd
     */
    public String getPwd() {
        return pwd;
    }
    /**
     * 设置 pwd
     * @param pwd 对pwd进行赋值
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    /**
     * 获取 cityNo
     * @return 返回 cityNo
     */
    public String getCityNo() {
        return cityNo;
    }
    /**
     * 设置 cityNo
     * @param cityNo 对cityNo进行赋值
     */
    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }
    /**
     * 获取 countryNo
     * @return 返回 countryNo
     */
    public String getCountryNo() {
        return countryNo;
    }
    /**
     * 设置 countryNo
     * @param countryNo 对countryNo进行赋值
     */
    public void setCountryNo(String countryNo) {
        this.countryNo = countryNo;
    }
    /**
     * 获取 brandNum
     * @return 返回 brandNum
     */
    public String getBrandNum() {
        return brandNum;
    }
    /**
     * 设置 brandNum
     * @param brandNum 对brandNum进行赋值
     */
    public void setBrandNum(String brandNum) {
        this.brandNum = brandNum;
    }
    /**
     * 获取 applyDate
     * @return 返回 applyDate
     */
    public String getApplyDate() {
        return applyDate;
    }
    /**
     * 设置 applyDate
     * @param applyDate 对applyDate进行赋值
     */
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
    /**
     * 获取 userName
     * @return 返回 userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置 userName
     * @param userName 对userName进行赋值
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取 userId
     * @return 返回 userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设置 userId
     * @param userId 对userId进行赋值
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获取 userpayMode
     * @return 返回 userpayMode
     */
    public String getUserpayMode() {
        return userpayMode;
    }
    /**
     * 设置 userpayMode
     * @param userpayMode 对userpayMode进行赋值
     */
    public void setUserpayMode(String userpayMode) {
        this.userpayMode = userpayMode;
    }
    /**
     * 获取 custIcType
     * @return 返回 custIcType
     */
    public String getCustIcType() {
        return custIcType;
    }
    /**
     * 设置 custIcType
     * @param custIcType 对custIcType进行赋值
     */
    public void setCustIcType(String custIcType) {
        this.custIcType = custIcType;
    }
    /**
     * 获取 custIcNo
     * @return 返回 custIcNo
     */
    public String getCustIcNo() {
        return custIcNo;
    }
    /**
     * 设置 custIcNo
     * @param custIcNo 对custIcNo进行赋值
     */
    public void setCustIcNo(String custIcNo) {
        this.custIcNo = custIcNo;
    }
    /**
     * 获取 contactTel
     * @return 返回 contactTel
     */
    public String getContactTel() {
        return contactTel;
    }
    /**
     * 设置 contactTel
     * @param contactTel 对contactTel进行赋值
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }
    /**
     * 获取 email
     * @return 返回 email
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置 email
     * @param email 对email进行赋值
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 获取 postCode
     * @return 返回 postCode
     */
    public String getPostCode() {
        return postCode;
    }
    /**
     * 设置 postCode
     * @param postCode 对postCode进行赋值
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    /**
     * 获取 sessionId
     * @return 返回 sessionId
     */
    public String getSessionId() {
        return sessionId;
    }
    /**
     * 设置 sessionId
     * @param sessionId 对sessionId进行赋值
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    /**
     * 获取 chValue
     * @return 返回 chValue
     */
    public String getChValue() {
        return chValue;
    }
    /**
     * 设置 chValue
     * @param chValue 对chValue进行赋值
     */
    public void setChValue(String chValue) {
        this.chValue = chValue;
    }
    /**
     * 获取 remoteAddr
     * @return 返回 remoteAddr
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }
    /**
     * 设置 remoteAddr
     * @param remoteAddr 对remoteAddr进行赋值
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }


    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

/*
 * 文 件 名:  User.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2017年8月29日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.model;

import java.io.Serializable;

/**
 * <用户表>
 * 
 * @author zhuyao 1824
 * @see [相关类/方法]
 */
public class User implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7540511516782082193L;

	/**
	 * 登录系统名 字母数字组合
	 */
	private String loginName;

	/**
	 * 登录用户中文名
	 */
	private String cnName;

	/**
	 * 用户邮箱
	 */
	private String email;

	/**
	 * 用户手机号
	 */
	private String phoneNum;

	/**
	 * 用户归属组织机构编码
	 */
	private String orgNum;

	/**
	 * 用户归属组织机构名称
	 */
	private String orgName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户密码加密盐
	 */
	private String salt;

	/**
	 * 是否可用 0 否 1 是
	 */
	private int available;

	/**
	 * 删除标记 0 未删除 1 已删除 2 不可删除
	 */
	private int deleteFlag;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 创建人编码
	 */
	private String createName;

	/**
	 * 修改时间
	 */
	private String updateTime;

	/**
	 * 修改人编码
	 */
	private String updateName;

	/**
	 * 最后登录时间
	 */
	private String lastLoginTime;

	/**
	 * 最后一次修改密码时间
	 */
	private String pwdUpdateTime;

	/**
	 * 最后一次修改密码时间和当前时间差（天数）
	 */
	private int pwdUpdateDays;

	/**
	 * 用户级别 1：商户、店铺 2：区县 3：地市 4：省、系统管理
	 */
	private int levelNum;

	/**
	 * 用户类型
	 */
	private String userTypeNum;

	/**
	 * 用户类型名称
	 */
	private String userTypeName;

	/**
	 * 店铺编码
	 */
	private String shopNum;

	/**
	 * 商户编码
	 */
	private String merchantNum;

	/**
	 * 区县编码
	 */
	private String areaNum;

	/**
	 * 区县名称
	 */
	private String areaName;

	/**
	 * 地市编码
	 */
	private String cityNum;

	/**
	 * 地市名称
	 */
	private String cityName;

	/**
	 * 角色编码
	 */
	private String roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 商户类型
	 */
	private String merchantType;
	/**
	 * 营业厅Boss组织机构编码只有是营业厅时该字段有值
	 */
	private String channelCode;
	/**
	 * 租户编码
	 */
	private String tenantNum;

	/**
	 * 对应的员工编码
	 */
	private String empNum;
	/**
	 * 用户对应机构级别编码
	 */
	private String orgLevel;
	/**
	 * 租户状态
	 */
	private String tenantStatus;
	/**
	 * 租户有效开始时间
	 */
	private String voildTimeBegin;
	/**
	 * 租户有效结束时间
	 */
	private String voildTimeEnd;

	// 身份证号
	private String idCard;

	public String getTenantNum() {
		return tenantNum;
	}

	public void setTenantNum(String tenantNum) {
		this.tenantNum = tenantNum;
	}

	/**
	 * 获取 pwdUpdateDays
	 * 
	 * @return 返回 pwdUpdateDays
	 */
	public int getPwdUpdateDays() {
		return pwdUpdateDays;
	}

	/**
	 * 设置 pwdUpdateDays
	 * 
	 * @param pwdUpdateDays 对pwdUpdateDays进行赋值
	 */
	public void setPwdUpdateDays(int pwdUpdateDays) {
		this.pwdUpdateDays = pwdUpdateDays;
	}

	/**
	 * 获取 loginName
	 * 
	 * @return 返回 loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置 loginName
	 * 
	 * @param loginName 对loginName进行赋值
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 获取 cnName
	 * 
	 * @return 返回 cnName
	 */
	public String getCnName() {
		return cnName;
	}

	/**
	 * 设置 cnName
	 * 
	 * @param cnName 对cnName进行赋值
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	/**
	 * 获取 email
	 * 
	 * @return 返回 email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 email
	 * 
	 * @param email 对email进行赋值
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取 phoneNum
	 * 
	 * @return 返回 phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * 设置 phoneNum
	 * 
	 * @param phoneNum 对phoneNum进行赋值
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * 获取 orgNum
	 * 
	 * @return 返回 orgNum
	 */
	public String getOrgNum() {
		return orgNum;
	}

	/**
	 * 设置 orgNum
	 * 
	 * @param orgNum 对orgNum进行赋值
	 */
	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	/**
	 * 获取 orgName
	 * 
	 * @return 返回 orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置 orgName
	 * 
	 * @param orgName 对orgName进行赋值
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取 password
	 * 
	 * @return 返回 password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置 password
	 * 
	 * @param password 对password进行赋值
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 salt
	 * 
	 * @return 返回 salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 设置 salt
	 * 
	 * @param salt 对salt进行赋值
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 获取 available
	 * 
	 * @return 返回 available
	 */
	public int getAvailable() {
		return available;
	}

	/**
	 * 设置 available
	 * 
	 * @param available 对available进行赋值
	 */
	public void setAvailable(int available) {
		this.available = available;
	}

	/**
	 * 获取 deleteFlag
	 * 
	 * @return 返回 deleteFlag
	 */
	public int getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * 设置 deleteFlag
	 * 
	 * @param deleteFlag 对deleteFlag进行赋值
	 */
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 获取 createTime
	 * 
	 * @return 返回 createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * 
	 * @param createTime 对createTime进行赋值
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 createName
	 * 
	 * @return 返回 createName
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * 设置 createName
	 * 
	 * @param createName 对createName进行赋值
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * 获取 updateTime
	 * 
	 * @return 返回 updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 * 
	 * @param updateTime 对updateTime进行赋值
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取 updateName
	 * 
	 * @return 返回 updateName
	 */
	public String getUpdateName() {
		return updateName;
	}

	/**
	 * 设置 updateName
	 * 
	 * @param updateName 对updateName进行赋值
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * 获取 lastLoginTime
	 * 
	 * @return 返回 lastLoginTime
	 */
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * 设置 lastLoginTime
	 * 
	 * @param lastLoginTime 对lastLoginTime进行赋值
	 */
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 获取 pwdUpdateTime
	 * 
	 * @return 返回 pwdUpdateTime
	 */
	public String getPwdUpdateTime() {
		return pwdUpdateTime;
	}

	/**
	 * 设置 pwdUpdateTime
	 * 
	 * @param pwdUpdateTime 对pwdUpdateTime进行赋值
	 */
	public void setPwdUpdateTime(String pwdUpdateTime) {
		this.pwdUpdateTime = pwdUpdateTime;
	}

	public String getCredentialsSalt() {
		return loginName + salt;
	}

	/**
	 * 获取 levelNum
	 * 
	 * @return 返回 levelNum
	 */
	public int getLevelNum() {
		return levelNum;
	}

	/**
	 * 设置 levelNum
	 * 
	 * @param levelNum 对levelNum进行赋值
	 */
	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	/**
	 * 获取 userTypeNum
	 * 
	 * @return 返回 userTypeNum
	 */
	public String getUserTypeNum() {
		return userTypeNum;
	}

	/**
	 * 设置 userTypeNum
	 * 
	 * @param userTypeNum 对userTypeNum进行赋值
	 */
	public void setUserTypeNum(String userTypeNum) {
		this.userTypeNum = userTypeNum;
	}

	/**
	 * 获取 shopNum
	 * 
	 * @return 返回 shopNum
	 */
	public String getShopNum() {
		return shopNum;
	}

	/**
	 * 设置 shopNum
	 * 
	 * @param shopNum 对shopNum进行赋值
	 */
	public void setShopNum(String shopNum) {
		this.shopNum = shopNum;
	}

	/**
	 * 获取 merchantNum
	 * 
	 * @return 返回 merchantNum
	 */
	public String getMerchantNum() {
		return merchantNum;
	}

	/**
	 * 设置 merchantNum
	 * 
	 * @param merchantNum 对merchantNum进行赋值
	 */
	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	/**
	 * 获取 areaNum
	 * 
	 * @return 返回 areaNum
	 */
	public String getAreaNum() {
		return areaNum;
	}

	/**
	 * 设置 areaNum
	 * 
	 * @param areaNum 对areaNum进行赋值
	 */
	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}

	/**
	 * 获取 cityNum
	 * 
	 * @return 返回 cityNum
	 */
	public String getCityNum() {
		return cityNum;
	}

	/**
	 * 设置 cityNum
	 * 
	 * @param cityNum 对cityNum进行赋值
	 */
	public void setCityNum(String cityNum) {
		this.cityNum = cityNum;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 获取 merchantType
	 * 
	 * @return 返回 merchantType
	 */
	public String getMerchantType() {
		return merchantType;
	}

	/**
	 * 设置 merchantType
	 * 
	 * @param merchantType 对merchantType进行赋值
	 */
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	/**
	 * 获取 empNum 员工编码
	 * 
	 * @return 返回 empNum
	 */
	public String getEmpNum() {
		return empNum;
	}

	/**
	 * 设置 empNum 员工编码
	 * 
	 * @param empNum 对empNum进行赋值
	 */
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getTenantStatus() {
		return tenantStatus;
	}

	public void setTenantStatus(String tenantStatus) {
		this.tenantStatus = tenantStatus;
	}

	public String getVoildTimeBegin() {
		return voildTimeBegin;
	}

	public void setVoildTimeBegin(String voildTimeBegin) {
		this.voildTimeBegin = voildTimeBegin;
	}

	public String getVoildTimeEnd() {
		return voildTimeEnd;
	}

	public void setVoildTimeEnd(String voildTimeEnd) {
		this.voildTimeEnd = voildTimeEnd;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}

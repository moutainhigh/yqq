package com.yqq.framework.model;

/**
 * 
 * <功能描述>
 * 
 * @author yangchuan
 * @see [相关类/方法]
 */
public class UserLog {
	private String logId;

	private String loginName;

	private String cnName;

	private String email;

	private String phoneNum;

	private String orgNum;

	private String password;

	private String salt;

	private int available;

	private int deleteFlag;

	private String createTime;

	private String createName;

	private String updateTime;

	private String updateName;

	private String lastloginTime;

	private String pwdUpdateTime;

	private String operTime;

	private String operName;

	private String operType;

	/**
	 * 用户类型
	 */
	private String userTypeNum;

	/**
	 * 租户编码
	 */
	private String tenantNum;

	public String getTenantNum() {
		return tenantNum;
	}

	public void setTenantNum(String tenantNum) {
		this.tenantNum = tenantNum;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getLastloginTime() {
		return lastloginTime;
	}

	public void setLastloginTime(String lastloginTime) {
		this.lastloginTime = lastloginTime;
	}

	public String getPwdUpdateTime() {
		return pwdUpdateTime;
	}

	public void setPwdUpdateTime(String pwdUpdateTime) {
		this.pwdUpdateTime = pwdUpdateTime;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
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
	 * @param userTypeNum
	 *            对userTypeNum进行赋值
	 */
	public void setUserTypeNum(String userTypeNum) {
		this.userTypeNum = userTypeNum;
	}

}
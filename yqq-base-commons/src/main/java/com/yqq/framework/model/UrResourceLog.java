/*
 * 文 件 名:  URResourceLog.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhangjunsheng 4724
 * 修改时间:  2017年8月31日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.model;

import java.io.Serializable;

/**
 * 权限配置 操作日志
 * 
 * @author zhangjunsheng 4724
 * @see [相关类/方法]
 */
public class UrResourceLog implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6266700312419305781L;

	/**
	 * 修改日志ID LOG+yyyyMMdd24HHMISS+SEQ_SYSTEM_ID
	 */
	private String logId;
	/**
	 * 用户登录名或角色id
	 */
	private String userRoleId;
	/**
	 * 系统资源编码
	 */
	private String resourceNum;
	/**
	 * 类型 ROLE-针对角色配置菜单权限 USER-单独用户配置菜单权限
	 */
	private String type;
	/**
	 * 修改时间yyyyMMdd24HHMISS
	 */
	private String updateTime;
	/**
	 * 修改人编码
	 */
	private String updateName;
	/**
	 * 操作时间yyyyMMdd24HHMISS
	 */
	private String operTime;
	/**
	 * 操作人编码
	 */
	private String operName;
	/**
	 * 操作类型 ADD 新增 EDIT 修改 DEL 删除
	 */
	private String operType;

	/**
	 * 获取 logId
	 * 
	 * @return 返回 logId
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 设置 logId
	 * 
	 * @param logId
	 *            对logId进行赋值
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}

	/**
	 * 获取 userRoleId
	 * 
	 * @return 返回 userRoleId
	 */
	public String getUserRoleId() {
		return userRoleId;
	}

	/**
	 * 设置 userRoleId
	 * 
	 * @param userRoleId
	 *            对userRoleId进行赋值
	 */
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * 获取 resourceNum
	 * 
	 * @return 返回 resourceNum
	 */
	public String getResourceNum() {
		return resourceNum;
	}

	/**
	 * 设置 resourceNum
	 * 
	 * @param resourceNum
	 *            对resourceNum进行赋值
	 */
	public void setResourceNum(String resourceNum) {
		this.resourceNum = resourceNum;
	}

	/**
	 * 获取 type
	 * 
	 * @return 返回 type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置 type
	 * 
	 * @param type
	 *            对type进行赋值
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @param updateTime
	 *            对updateTime进行赋值
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
	 * @param updateName
	 *            对updateName进行赋值
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * 获取 operTime
	 * 
	 * @return 返回 operTime
	 */
	public String getOperTime() {
		return operTime;
	}

	/**
	 * 设置 operTime
	 * 
	 * @param operTime
	 *            对operTime进行赋值
	 */
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	/**
	 * 获取 operName
	 * 
	 * @return 返回 operName
	 */
	public String getOperName() {
		return operName;
	}

	/**
	 * 设置 operName
	 * 
	 * @param operName
	 *            对operName进行赋值
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}

	/**
	 * 获取 operType
	 * 
	 * @return 返回 operType
	 */
	public String getOperType() {
		return operType;
	}

	/**
	 * 设置 operType
	 * 
	 * @param operType
	 *            对operType进行赋值
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}

}

/*
 * 文 件 名:  Role.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2017年8月30日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.model;

import java.io.Serializable;

/**
 * <系统角色>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class Role implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1026603985339963550L;

	/**
	 * 角色ID
	 */
	private String roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 是否可用 0 否 1 是
	 */
	private int available;
	
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

	/**
	 * 获取 roleId
	 * @return 返回 roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设置 roleId
	 * @param roleId 对roleId进行赋值
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取 roleName
	 * @return 返回 roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置 roleName
	 * @param roleName 对roleName进行赋值
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 获取 description
	 * @return 返回 description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 description
	 * @param description 对description进行赋值
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取 available
	 * @return 返回 available
	 */
	public int getAvailable() {
		return available;
	}

	/**
	 * 设置 available
	 * @param available 对available进行赋值
	 */
	public void setAvailable(int available) {
		this.available = available;
	}
	
}

/*
 * 文 件 名:  UserRoleResource.java
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
 * 角色或用户与系统菜单资源对照关系
 * 
 * @author zhangjunsheng 4724
 * @see [相关类/方法]
 */
public class UserRoleResource implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 166858181158297848L;
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

}

package com.yqq.framework.model;

import java.io.Serializable;

/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class RoleResources implements Serializable {

	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2892066741222751081L;

    /**
	 * 角色ID
	 */
	private String  roleId;

	/**
	 * 菜单ID
	 */
	private String  resourceNum;

	/**
	 * 平台ID
	 * 
	 * @return
	 */
	private String  paltformNum;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceNum() {
		return resourceNum;
	}

	public void setResourceNum(String resourceNum) {
		this.resourceNum = resourceNum;
	}

	public String getPaltformNum() {
		return paltformNum;
	}

	public void setPaltformNum(String paltformNum) {
		this.paltformNum = paltformNum;
	}
	
}

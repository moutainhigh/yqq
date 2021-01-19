/*
 * 文 件 名:  RoleLog.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wangshaokun 4674
 * 修改时间:  2017年8月30日
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
public class RoleLog implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -800735613751340171L;

    /**
     * 日志ID LOG+yyyyMMdd24HHMISS+SEQ_SYSTEM_ID
     */
    private String logId;
    
    /**
     * 角色ID TR+SEQ_SYSTEM_ID
     */
    private String roleId;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色描述
     */
    private String description;
    
    /**
     * 是否可用  0 否 1 是
     */
    private int available;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
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
    
}
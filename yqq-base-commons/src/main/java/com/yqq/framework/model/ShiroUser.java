/*
 * 文 件 名:  ShiroUser.java
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
import java.util.Objects;

/**
 * <功能描述>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class ShiroUser implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4168316446524342071L;
	
	/**
	 * 用户登录名
	 */
	public String loginName;
	
	/**
	 * 用户中文名
	 */
    public String cnName;
    
    /**
     * 用户级别  1：商户、店铺 2：区县 3：地市 4：省、系统管理
     */
    public int levelNum;
    
    /**
     * 用户类型
     */
    public String userTypeNum;
    
    /**
     * 店铺编码
     */
    public String shopNum;
    
    /**
     * 商户编码
     */
    public String merchantNum;
    
    /**
     * 区县编码
     */
    public String areaNum;
    
    /**
     * 地市编码
     */
    public String cityNum;
    
    /**
     * 用户归属组织机构编码
     */
    public String orgNum;
    
    /**
     * 角色ID
     */
    public String roleId;
    
    /**
     * 角色名称
     */
    public String roleName;

    /**
     * 营业厅Boss组织机构编码只有是营业厅时该字段有值
     */
    private String channelCode;
    
    /**
	 * 租户编码
	 */
	private String tenantNum;
	
	private String empNum;
	
	/**
	 * 是否第一次登录 0否 1是
	 */
	private int  isFirstLogin;
    /**
     * 用户对应机构级别编码
     */
    private String orgLevel;
    /**
     * 租户登录标记  租户状态正常并且不在生效日期范围内AdminConstants.TENANT_VOILD_TIME   或 租户状态为冻结 AdminConstants.TENANT_STATUS_DJ；
     */
    private String tenantLoginFlag;
	
	public int getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(int isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	/**
	 * 获取 empNum
	 * @return 返回 empNum
	 */
	public String getEmpNum() {
		return empNum;
	}

	/**
	 * 设置 empNum
	 * @param empNum 对empNum进行赋值
	 */
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getTenantNum() {
		return tenantNum;
	}

	public void setTenantNum(String tenantNum) {
		this.tenantNum = tenantNum;
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

	public String getCnName() {
        return cnName;
    }

    /**
	 * 获取 loginName
	 * @return 返回 loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置 loginName
	 * @param loginName 对loginName进行赋值
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 获取 orgNum
	 * @return 返回 orgNum
	 */
	public String getOrgNum() {
		return orgNum;
	}

	/**
	 * 设置 orgNum
	 * @param orgNum 对orgNum进行赋值
	 */
	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	/**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }
    
    /**
	 * 获取 levelNum
	 * @return 返回 levelNum
	 */
	public int getLevelNum() {
		return levelNum;
	}

	/**
	 * 设置 levelNum
	 * @param levelNum 对levelNum进行赋值
	 */
	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	/**
	 * 获取 userTypeNum
	 * @return 返回 userTypeNum
	 */
	public String getUserTypeNum() {
		return userTypeNum;
	}

	/**
	 * 设置 userTypeNum
	 * @param userTypeNum 对userTypeNum进行赋值
	 */
	public void setUserTypeNum(String userTypeNum) {
		this.userTypeNum = userTypeNum;
	}

	/**
	 * 获取 shopNum
	 * @return 返回 shopNum
	 */
	public String getShopNum() {
		return shopNum;
	}

	/**
	 * 设置 shopNum
	 * @param shopNum 对shopNum进行赋值
	 */
	public void setShopNum(String shopNum) {
		this.shopNum = shopNum;
	}

	/**
	 * 获取 merchantNum
	 * @return 返回 merchantNum
	 */
	public String getMerchantNum() {
		return merchantNum;
	}

	/**
	 * 设置 merchantNum
	 * @param merchantNum 对merchantNum进行赋值
	 */
	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	/**
	 * 获取 areaNum
	 * @return 返回 areaNum
	 */
	public String getAreaNum() {
		return areaNum;
	}

	/**
	 * 设置 areaNum
	 * @param areaNum 对areaNum进行赋值
	 */
	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}

	/**
	 * 获取 cityNum
	 * @return 返回 cityNum
	 */
	public String getCityNum() {
		return cityNum;
	}

	/**
	 * 设置 cityNum
	 * @param cityNum 对cityNum进行赋值
	 */
	public void setCityNum(String cityNum) {
		this.cityNum = cityNum;
	}

	/**
	 * 设置 cnName
	 * @param cnName 对cnName进行赋值
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getTenantLoginFlag() {
        return tenantLoginFlag;
    }

    public void setTenantLoginFlag(String tenantLoginFlag) {
        this.tenantLoginFlag = tenantLoginFlag;
    }

    /**
     * 重载hashCode,只计算loginName;
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(loginName);
    }

    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ShiroUser other = (ShiroUser) obj;
        if (loginName == null) {
            return other.loginName == null;
        } else return loginName.equals(other.loginName);
    }
}

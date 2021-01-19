/*
 * 文 件 名:  Resource.java
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
import java.util.List;

/**
 * <资源配置>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class Resource implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4001494297368896686L;
	
	/**
	 * 资源编码
	 */
	private String resourceNum;
	
	/**
	 * 应用编码 
	 */
	private String appNum;
	
	/**
	 * 资源名称
	 */
	private String resourceName;
	
	/**
	 * 资源类型 
	 */
	private String resourceType;
	
	/**
	 * 资源访问地址
	 */
	private String resourceUrl;
	
	/**
	 * 直接父级编码
	 */
	private String parentNum;
	
	/**
	 * 菜单权限编码
	 */
	private String permission;
	
	/**
	 * 是否可用
	 */
	private int available;
	
	/**
	 * 菜单图标css样式
	 */
	private String styleClass;
	
	/**
	 * 菜单显示顺序
	 */
	private int sort;
	
	/**
	 * 是否当前资源
	 */
	private Boolean current;
	
	/**
	 * 子资源
	 */
	private List<Resource> subResourceList;

	public enum ResourceType {
	    /**
	     * 模块
	     */
		MODULE("MODULE"),
		/**
		 *菜单
		 */
		MENU("MENU"),
		/**
		 * 按钮
		 */
		BUTTON("BUTTON");

        private final String type;
        ResourceType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
	
	/**
	 * 获取 resourceNum
	 * @return 返回 resourceNum
	 */
	public String getResourceNum() {
		return resourceNum;
	}

	/**
	 * 设置 resourceNum
	 * @param resourceNum 对resourceNum进行赋值
	 */
	public void setResourceNum(String resourceNum) {
		this.resourceNum = resourceNum;
	}

	/**
	 * 获取 appNum
	 * @return 返回 appNum
	 */
	public String getAppNum() {
		return appNum;
	}

	/**
	 * 设置 appNum
	 * @param appNum 对appNum进行赋值
	 */
	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}

	/**
	 * 获取 resourceName
	 * @return 返回 resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * 设置 resourceName
	 * @param resourceName 对resourceName进行赋值
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * 获取 resourceType
	 * @return 返回 resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * 设置 resourceType
	 * @param resourceType 对resourceType进行赋值
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * 获取 resourceUrl
	 * @return 返回 resourceUrl
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}

	/**
	 * 设置 resourceUrl
	 * @param resourceUrl 对resourceUrl进行赋值
	 */
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	/**
	 * 获取 parentNum
	 * @return 返回 parentNum
	 */
	public String getParentNum() {
		return parentNum;
	}

	/**
	 * 设置 parentNum
	 * @param parentNum 对parentNum进行赋值
	 */
	public void setParentNum(String parentNum) {
		this.parentNum = parentNum;
	}

	/**
	 * 获取 permission
	 * @return 返回 permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 设置 permission
	 * @param permission 对permission进行赋值
	 */
	public void setPermission(String permission) {
		this.permission = permission;
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

	/**
	 * 获取 styleClass
	 * @return 返回 styleClass
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * 设置 styleClass
	 * @param styleClass 对styleClass进行赋值
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * 获取 sort
	 * @return 返回 sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * 设置 sort
	 * @param sort 对sort进行赋值
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * 获取 current
	 * @return 返回 current
	 */
	public Boolean getCurrent() {
		return current;
	}

	/**
	 * 设置 current
	 * @param current 对current进行赋值
	 */
	public void setCurrent(Boolean current) {
		this.current = current;
	}

	/**
	 * 获取 subResourceList
	 * @return 返回 subResourceList
	 */
	public List<Resource> getSubResourceList() {
		return subResourceList;
	}

	/**
	 * 设置 subResourceList
	 * @param subResourceList 对subResourceList进行赋值
	 */
	public void setSubResourceList(List<Resource> subResourceList) {
		this.subResourceList = subResourceList;
	}
	
}

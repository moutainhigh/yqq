package com.yqq.framework.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class Menu implements Serializable {
    
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1078143046103842351L;

    private String resourceNum;

	private String resourceName;

	private ResourceType type;

	private String resourceType;

	private String resourceUrl;

	private String parentNum;

	private String permission;

	private int available;

	private int sort;

	private String appNum;

	private boolean current;

	private List<com.yqq.framework.model.SubMenu> subMenus;

	private String styleClass;

	private int isChecked;

	private int isReadonly;
	
	private String platformNum;

	public enum ResourceType {
		/**
		 * 菜单
		 */
	    menu("菜单"), 
	    /**
	     * 按钮
	     */
	    button("按钮");

		private final String info;

		ResourceType(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

	public String getResourceNum() {
		return resourceNum;
	}

	public void setResourceNum(String resourceNum) {
		this.resourceNum = resourceNum;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getParentNum() {
		return parentNum;
	}

	public void setParentNum(String parentNum) {
		this.parentNum = parentNum;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getAppNum() {
		return appNum;
	}

	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public List<com.yqq.framework.model.SubMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<com.yqq.framework.model.SubMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}

	public int getIsReadonly() {
		return isReadonly;
	}

	public void setIsReadonly(int isReadonly) {
		this.isReadonly = isReadonly;
	}

	public String getPlatformNum() {
		return platformNum;
	}

	public void setPlatformNum(String platformNum) {
		this.platformNum = platformNum;
	}

	
}

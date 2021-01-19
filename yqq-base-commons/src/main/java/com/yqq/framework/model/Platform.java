/*
 * 文 件 名:  Platform.java
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
 * <功能描述>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class Platform implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7675650723771195513L;

	/**
	 * 平台编码
	 */
	private String platformNum;
	
	/**
	 * 平台名称
	 */
	private String platformName;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 平台描述
	 */
	private String description;
	
	/**
	 * 是否启用
	 */
	private int available;
	
	/**
	 * 切换平台首页url
	 */
	private String homeUrl;

	/**
	 * 获取 platformNum
	 * @return 返回 platformNum
	 */
	public String getPlatformNum() {
		return platformNum;
	}

	/**
	 * 设置 platformNum
	 * @param platformNum 对platformNum进行赋值
	 */
	public void setPlatformNum(String platformNum) {
		this.platformNum = platformNum;
	}

	/**
	 * 获取 platformName
	 * @return 返回 platformName
	 */
	public String getPlatformName() {
		return platformName;
	}

	/**
	 * 设置 platformName
	 * @param platformName 对platformName进行赋值
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	/**
	 * 获取 createTime
	 * @return 返回 createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * @param createTime 对createTime进行赋值
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	/**
	 * 获取 homeUrl
	 * @return 返回 homeUrl
	 */
	public String getHomeUrl() {
		return homeUrl;
	}

	/**
	 * 设置 homeUrl
	 * @param homeUrl 对homeUrl进行赋值
	 */
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}
	
}

/*
 * 文 件 名:  Application.java
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
 * <应用bean>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class Application implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2966814778948157290L;
	
	/**
	 * 应用编码
	 */
	private String appNum;
	
	/**
	 * 平台编码
	 */
	private String platformNum;
	
	/**
	 * 应用名称
	 */
	private String appName;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 应用描述
	 */
	private String description;
	
	/**
	 * 是否启用
	 */
	private int available;
	
	/**
	 * 应用地址
	 */
	private String url;

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
	 * 获取 appName
	 * @return 返回 appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * 设置 appName
	 * @param appName 对appName进行赋值
	 */
	public void setAppName(String appName) {
		this.appName = appName;
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
	 * 获取 url
	 * @return 返回 url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置 url
	 * @param url 对url进行赋值
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}

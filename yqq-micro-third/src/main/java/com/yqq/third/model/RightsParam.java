/*
 * 文 件 名:  RightsParam.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2020,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  jiangtao 1806
 * 修改时间:  2020年3月9日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.model;

import java.io.Serializable;

/**
 * <权益对接参数值>
 * 
 * @author jiangtao 1806
 * @see [相关类/方法]
 */
public class RightsParam implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 权益编码
	 */
	private String rightsNum;

	/**
	 * 参数编码
	 */
	private String paramNum;

	/**
	 * 参数名称
	 */
	private String paramName;
	
	/**
	 * 参数值
	 */
	private String paramValue;

	public String getRightsNum() {
		return rightsNum;
	}

	public void setRightsNum(String rightsNum) {
		this.rightsNum = rightsNum;
	}

	public String getParamNum() {
		return paramNum;
	}

	public void setParamNum(String paramNum) {
		this.paramNum = paramNum;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	

}

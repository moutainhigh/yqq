package com.yqq.framework.model;

import java.io.Serializable;

/**
 * 区域实体 
 * @author xwtec_jt
 *
 */
public class AreaInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 市编码
	 */
	private String cityNum;

	/**
	 * 区域编码
	 */
	private String areaNum;

	/**
	 * 地市名称
	 */
	private String areaName;

	public String getCityNum() {
		return cityNum;
	}

	public void setCityNum(String cityNum) {
		this.cityNum = cityNum;
	}

	public String getAreaNum() {
		return areaNum;
	}

	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}

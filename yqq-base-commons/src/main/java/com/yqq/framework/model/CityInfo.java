package com.yqq.framework.model;

import java.io.Serializable;
import java.util.List;

/**
 * 地市实体
 * @author xwtec_jt
 *
 */
public class CityInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 地市编码
	 */
	private String cityNum;
	
	/**
	 * 地市名称
	 */
	private String cityName;
	
	/**
	 * 区县列表
	 */
	private List<AreaInfo> areaList;

	public String getCityNum() {
		return cityNum;
	}

	public void setCityNum(String cityNum) {
		this.cityNum = cityNum;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<AreaInfo> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaInfo> areaList) {
		this.areaList = areaList;
	}
	
}

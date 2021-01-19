/**
 * 
 */
package com.yqq.framework.model;

import java.io.Serializable;

/**
 * @author xwtec_jt
 *
 */
public class UserType implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户类型
	 */
	private String userTypeNum;

	/**
	 * 用户类型名称
	 */
	private String userTypeName;

	/**
	 * 用户级别
	 */
	private String levelNum;

	public String getUserTypeNum() {
		return userTypeNum;
	}

	public void setUserTypeNum(String userTypeNum) {
		this.userTypeNum = userTypeNum;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(String levelNum) {
		this.levelNum = levelNum;
	}

}

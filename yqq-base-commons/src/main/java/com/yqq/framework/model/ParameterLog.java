/*
 * 文 件 名:  ParameterLog.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhangjunsheng 4724
 * 修改时间:  2017年8月30日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.model;

import java.io.Serializable;

/**
 * <系统参数操作日志表>
 * 
 * @author zhangjunsheng 4724
 * @see [相关类/方法]
 */
public class ParameterLog implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2418414342782391283L;

	/**
	 * 修改日志ID LOG+yyyyMMdd24HHMISS+SEQ_SYSTEM_ID
	 */
	private String logId;
	/**
	 * 应用id TSYS_APPLICATION表主键
	 */
	private String appNum;
	/**
	 * 系统参数编码
	 */
	private String parameterNum;
	/**
	 * 系统参数名称
	 */
	private String parameterName;
	/**
	 * 系统参数值
	 */
	private String parameterValue;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否可以页面修改 0 否 1 是
	 */
	private int editFlag;
	/**
	 * 创建时间yyyyMMdd24HHMISS
	 */
	private String createTime;
	/**
	 * 创建人编码
	 */
	private String createName;
	/**
	 * 修改时间yyyyMMdd24HHMISS
	 */
	private String updateTime;
	/**
	 * 修改人编码
	 */
	private String updateName;
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
	 * 获取 logId
	 * 
	 * @return 返回 logId
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 设置 logId
	 * 
	 * @param logId
	 *            对logId进行赋值
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}

	/**
	 * 获取 parameterNum
	 * 
	 * @return 返回 parameterNum
	 */
	public String getParameterNum() {
		return parameterNum;
	}

	/**
	 * 设置 parameterNum
	 * 
	 * @param parameterNum
	 *            对parameterNum进行赋值
	 */
	public void setParameterNum(String parameterNum) {
		this.parameterNum = parameterNum;
	}

	/**
	 * 获取 parameterName
	 * 
	 * @return 返回 parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * 设置 parameterName
	 * 
	 * @param parameterName
	 *            对parameterName进行赋值
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * 获取 parameterValue
	 * 
	 * @return 返回 parameterValue
	 */
	public String getParameterValue() {
		return parameterValue;
	}

	/**
	 * 设置 parameterValue
	 * 
	 * @param parameterValue
	 *            对parameterValue进行赋值
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	/**
	 * 获取 remark
	 * 
	 * @return 返回 remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 remark
	 * 
	 * @param remark
	 *            对remark进行赋值
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取 editFlag
	 * 
	 * @return 返回 editFlag
	 */
	public int getEditFlag() {
		return editFlag;
	}

	/**
	 * 设置 editFlag
	 * 
	 * @param editFlag
	 *            对editFlag进行赋值
	 */
	public void setEditFlag(int editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * 获取 createTime
	 * 
	 * @return 返回 createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * 
	 * @param createTime
	 *            对createTime进行赋值
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 createName
	 * 
	 * @return 返回 createName
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * 设置 createName
	 * 
	 * @param createName
	 *            对createName进行赋值
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * 获取 updateTime
	 * 
	 * @return 返回 updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
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
	 * 设置 updateTime
	 * 
	 * @param updateTime
	 *            对updateTime进行赋值
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取 updateName
	 * 
	 * @return 返回 updateName
	 */
	public String getUpdateName() {
		return updateName;
	}

	/**
	 * 设置 updateName
	 * 
	 * @param updateName
	 *            对updateName进行赋值
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * 获取 operTime
	 * 
	 * @return 返回 operTime
	 */
	public String getOperTime() {
		return operTime;
	}

	/**
	 * 设置 operTime
	 * 
	 * @param operTime
	 *            对operTime进行赋值
	 */
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	/**
	 * 获取 operName
	 * 
	 * @return 返回 operName
	 */
	public String getOperName() {
		return operName;
	}

	/**
	 * 设置 operName
	 * 
	 * @param operName
	 *            对operName进行赋值
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}

	/**
	 * 获取 operType
	 * 
	 * @return 返回 operType
	 */
	public String getOperType() {
		return operType;
	}

	/**
	 * 设置 operType
	 * 
	 * @param operType
	 *            对operType进行赋值
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}

}

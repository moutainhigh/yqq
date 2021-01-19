/*
 * 文 件 名:  RightsLeadReq.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2020年3月2日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.model.request.wap;

import com.yqq.third.model.UserInfoBean;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <会员领取权益包请求信息>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
@ApiModel(value="会员领取权益包请求信息")
public class RightsLeadReq implements Serializable{
	
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7106030201429485904L;

    /**
	 * -会员ID
	 */
	@NotEmpty(message="会员ID不能为空")
	private String memberId;
	 
	/**
	 * -会员类型编码
	 */
	private String memberTypeNum;
	
	/**
	 * -权益包编码
	 */
	private String rightsBodyNum;
	
	/**
	 * -权益编码
	 */
	@NotEmpty(message="权益编码不能为空")
	private String rightsNum;
	
	/**
	 * 渠道编码
	 */
	@NotEmpty(message="渠道编码不能为空")
	private String channelNum;
	
	/**
	 * 下发类型 AUTO 自动   HANDLE 手工  REISSUE补发
	 */
	@NotEmpty(message="下发类型不能为空")
	private String handleType;
	
	/**
	 * -特殊日期标志 BIRTHDAY-生日，可为空
	 */
	private String onceType;
	
	/**
	 * 可为空，如果为空，取系统档期日期;格式：YYYYMMDDHH24MISS
	 */
	private String leadTime;
	
	/**
	 * 领取数量
	 */
	@Max(value=1,message="领取数量不能小于1")
	private Integer leadCount;
	
	/**
	 * 江苏移动用户信息
	 */
	@NotNull(message="用户信息不能为空")
	private UserInfoBean userInfoBean;

	/**
	 * 领取记录号码
	 */
    @NotNull(message="领取记录号码不能为空")
	private String drawNum;

	/**
	 * 获取 memberId
	 * @return 返回 memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * 设置 memberId
	 * @param memberId 对memberId进行赋值
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * 获取 memberTypeNum
	 * @return 返回 memberTypeNum
	 */
	public String getMemberTypeNum() {
		return memberTypeNum;
	}

	/**
	 * 设置 memberTypeNum
	 * @param memberTypeNum 对memberTypeNum进行赋值
	 */
	public void setMemberTypeNum(String memberTypeNum) {
		this.memberTypeNum = memberTypeNum;
	}

	/**
	 * 获取 rightsBodyNum
	 * @return 返回 rightsBodyNum
	 */
	public String getRightsBodyNum() {
		return rightsBodyNum;
	}

	/**
	 * 设置 rightsBodyNum
	 * @param rightsBodyNum 对rightsBodyNum进行赋值
	 */
	public void setRightsBodyNum(String rightsBodyNum) {
		this.rightsBodyNum = rightsBodyNum;
	}

	/**
	 * 获取 rightsNum
	 * @return 返回 rightsNum
	 */
	public String getRightsNum() {
		return rightsNum;
	}

	/**
	 * 设置 rightsNum
	 * @param rightsNum 对rightsNum进行赋值
	 */
	public void setRightsNum(String rightsNum) {
		this.rightsNum = rightsNum;
	}

	/**
	 * 获取 channelNum
	 * @return 返回 channelNum
	 */
	public String getChannelNum() {
		return channelNum;
	}

	/**
	 * 设置 channelNum
	 * @param channelNum 对channelNum进行赋值
	 */
	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	/**
	 * 获取 handleType
	 * @return 返回 handleType
	 */
	public String getHandleType() {
		return handleType;
	}

	/**
	 * 设置 handleType
	 * @param handleType 对handleType进行赋值
	 */
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	/**
	 * 获取 onceType
	 * @return 返回 onceType
	 */
	public String getOnceType() {
		return onceType;
	}

	/**
	 * 设置 onceType
	 * @param onceType 对onceType进行赋值
	 */
	public void setOnceType(String onceType) {
		this.onceType = onceType;
	}

	/**
	 * 获取 leadTime
	 * @return 返回 leadTime
	 */
	public String getLeadTime() {
		return leadTime;
	}

	/**
	 * 设置 leadTime
	 * @param leadTime 对leadTime进行赋值
	 */
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * 获取 leadCount
	 * @return 返回 leadCount
	 */
	public Integer getLeadCount() {
		return leadCount;
	}

	/**
	 * 设置 leadCount
	 * @param leadCount 对leadCount进行赋值
	 */
	public void setLeadCount(Integer leadCount) {
		this.leadCount = leadCount;
	}

	/**
	 * 获取 userInfoBean
	 * @return 返回 userInfoBean
	 */
	public UserInfoBean getUserInfoBean() {
		return userInfoBean;
	}

	/**
	 * 设置 userInfoBean
	 * @param userInfoBean 对userInfoBean进行赋值
	 */
	public void setUserInfoBean(UserInfoBean userInfoBean) {
		this.userInfoBean = userInfoBean;
	}

	/**
	 * 获取 领取记录号码
	 *
	 * @return drawNum 领取记录号码
	 */
	public String getDrawNum() {
		return this.drawNum;
	}

	/**
	 * 设置 领取记录号码
	 *
	 * @param drawNum 领取记录号码
	 */
	public void setDrawNum(String drawNum) {
		this.drawNum = drawNum;
	}
}

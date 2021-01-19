/*
 * 文 件 名:  GoodsSeqEnum.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2018年8月8日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.commons.enums;

/**
 * 业务序列定义
 *
 * @author yangchuan
 * @see [相关类/方法]
 */
public enum BizSeqTypeEnum {

	/**
	 * 权益编码
	 */
	RIGHTS_NUM("R"),

	/**
	 * 权益采购批次编码
	 */
	RIGHTS_STOCK_NUM("RS"),

	/**
	 * 权益补发编码
	 */
	RIGHTS_REISSUE_NUM("RR"),

	/**
	 * 权益商户编码
	 */
	RIFHTS_OWNER_NUM("RO"),

	/**
	 * 白名单用户ID
	 */
	WHITE_MEMBER_ID("WM"),

	/**
	 * 白名单分组ID
	 */
	WHITE_GROUP_ID("WG"),

	/**
	 * 权益商户合同ID
	 */
	OWNER_CONTRACT_ID("OC"),

	/**
	 * 权益分销商户编码
	 */
	RIFHTS_DIST_MERCH_NUM("RDM"),

	/**
	 * 分销批次编码
	 */
	RIFHTS_DIST_BATCH_NUM("RDB"),
	
	/**
	 * 导出记录号
	 */
	RIFHTS_DIST_LOAD_NUM("RDL"),
	
	/**
	 * 组织机构编码
	 */
	RIFHTS_ORG_NUM("TO"),
	
	/**
	 * 员工号
	 */
	ORG_EMP_NUM("OEN"),
	
	/**
	 * 分组编码
	 */
	RIGHTS_GROUP_NUM("G");

	;

	private String numVal;

	BizSeqTypeEnum(String numVal) {
		this.numVal = numVal;
	}

	/**
	 * 获取 numVal
	 *
	 * @return 返回 numVal
	 */
	public String geNumVal() {
		return numVal;
	}

}

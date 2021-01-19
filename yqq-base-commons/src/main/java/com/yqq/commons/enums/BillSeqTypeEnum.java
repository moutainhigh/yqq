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
 * 单据序列定义
 *
 * @author yangchuan
 * @see [相关类/方法]
 */
public enum BillSeqTypeEnum {
	/**
	 * 公共单据序列
	 */
	PUBLIC_NUM("1"),

	/**
	 * 分销批次编码
	 */
	RIFHTS_DIST_BATCH_BILL_NUM("RDBB"),

	/**
	 * 分销权益单据
	 */
	RIGHTS_DIST_BILL_NUM("RDB"),

	/**
	 * 门店权益单据
	 */
	RIGHTS_BILL_NUM("RB"),

	/**
	 * 权益商城单据
	 */
	RIGHTS_ONLINE_BILL_NUM("ROB"),
	
	/**
	 * 权益商城库存单据
	 */
	RIGHTS_ONLINE_STOCK_BILL_NUM("ROSB"),

	;
	private String numVal;

	BillSeqTypeEnum(String numVal) {
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

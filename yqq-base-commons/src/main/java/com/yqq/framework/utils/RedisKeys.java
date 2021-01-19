package com.yqq.framework.utils;


/***
 * redis key的含义
 * @author lin.mr
 *
 */
public class RedisKeys {

	/**
	 * 场景审核，目标库用户 写入redis Key + "actId"
	 */
	public  static final String  ACTIVITYTARGET = "ACTIVITY_TARGET_";

	/**
	 *  活动----目标库数据 写入redis
	 */
	public  static final String  LOTTERYCUSTOMER = "LOTTERY_CUSTOMER_";

	/**
	 *  活动----中奖黑名单数据 写入redis
	 */
	public  static final String  LOTTERYWINBLOCK = "LOTTERY_WIN_BLOCK_";

	/**
	 *  活动----H黑名单数据 写入redis
	 */
	public  static final String  LOTTERYBLOCK = "LOTTERY_BLOCK_";
	/**
	 *  活动----奖品 写入redis
	 */
	public  static final String  LOTTERPRIZE = "Lottery:Prize:Count:";
}

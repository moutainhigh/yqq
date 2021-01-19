/*
 * 文 件 名:  BargainUtils.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2019年2月25日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


/**
 * <砍价算法工具类>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class BargainUtils {

	/**
	 * 按最大砍价次数获取砍价金额
	 * @param totalBargainMoney 可砍价总金额
	 * @param leftBargainMoney 剩余砍价金额
	 * @param totalBargainTimes 可砍价总次数
	 * @param leftBargainTimes 剩余砍价次数
	 * @param scale 保留几位小数
	 * @return 砍价金额
	 */
	public static BigDecimal getBargainMoneyByTimes(BigDecimal totalBargainMoney, BigDecimal leftBargainMoney, int totalBargainTimes, int leftBargainTimes, int scale){
		if (leftBargainTimes <= 0) {
            throw new IllegalArgumentException("leftBargainTimes should gte 1");
        }
        if (leftBargainTimes == 1) {
            return leftBargainMoney;
        }
        // 砍价下限金额=砍价总金额/(砍价总次数*2)
        BigDecimal minBargain = totalBargainMoney.divide(BigDecimal.valueOf(totalBargainTimes).multiply(BigDecimal.valueOf(2)), RoundingMode.HALF_EVEN);
        double min = minBargain.doubleValue();
        // 砍价上限金额=剩余砍价金额-砍价下限金额*剩余砍价次数
        double max = leftBargainMoney.doubleValue() - min * leftBargainTimes;
        double ratio = new Random().nextDouble();
        return BigDecimal.valueOf(min + (leftBargainTimes > (2*totalBargainTimes/3) ? max / 3 : max) * ratio).setScale(scale, RoundingMode.HALF_EVEN);
	}
	
	/**
	 * 按每次最大和最小砍价金额获取砍价金额
	 * @param leftBargainMoney 剩余可砍价金额
	 * @param maxBargainMoney 每次最大砍价金额
	 * @param scale 保留小数位数
	 * @return
	 */
	public static BigDecimal getBargainMoneyByMoney(BigDecimal leftBargainMoney, BigDecimal maxBargainMoney, int scale){
        double max = maxBargainMoney.doubleValue();
        double left = leftBargainMoney.doubleValue();
		if (max <= 0) {
            throw new IllegalArgumentException("maxBargainMoney should gt 0");
        }
		if (left <= 0) {
            throw new IllegalArgumentException("leftBargainMoney should gt 0");
        }
		if(left < max){
			return leftBargainMoney;
		}
        double ratio = new Random().nextDouble();
        BigDecimal bargainMoney = BigDecimal.valueOf(max - max * ratio).setScale(scale, RoundingMode.HALF_EVEN);
        return bargainMoney;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
            int t = new Random().nextInt(20) + 1;
            int scale = 2;
            BigDecimal tt = BigDecimal.valueOf(new Random().nextDouble() * 200 + 1).setScale(scale, RoundingMode.HALF_UP);
            BigDecimal reduced = BigDecimal.valueOf(0);
            for (int j = 0; j < t; j++) {
                BigDecimal current = BargainUtils.getBargainMoneyByTimes(tt, tt.subtract(reduced), t, t - j, scale);
                reduced = reduced.add(current);
                System.out.println("current:"+current);
            }
            System.out.println("reduced:"+reduced);
            System.out.println("tt:"+tt);
        }
		
		for (int i = 0; i < 1; i++) {
			int scale = 2;
			BigDecimal tt = BigDecimal.valueOf(100).setScale(scale, RoundingMode.HALF_UP);
			System.out.println("tt:"+tt);
			BigDecimal reduced = BigDecimal.valueOf(0);
            for (int j = 0; j < 100; j++) {
                BigDecimal current = BargainUtils.getBargainMoneyByMoney( tt.subtract(reduced), new BigDecimal(20), scale);
                reduced = reduced.add(current);
                System.out.println("current:"+current);
                System.out.println("reduced:"+reduced);
            }
            
           
        }
		
	}
}

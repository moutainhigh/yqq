/*
 * 文 件 名:  SHAUtils.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年4月20日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class SHAUtils {
    public static final String KEY_SHA = "SHA-256";
    public static final String ra = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 字符串sha加密
     * @param s 要加密的字符串
     * @return 加密后的字符串
     */
    public static String sha(String s) {
        BigInteger sha = null;
        byte[] bys = s.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(bys);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha.toString(32);
    }

    /**
     * 字符串+随机盐 sha加密
     * @param tenantNum 要加密的字符串
     * @return 盐和加密后的字符串
     */
    public static Map<String, String> getResult(String tenantNum, String salt) {
        if(StringUtils.isEmpty(salt)) {
            salt = getSalt();
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("salt", salt);// 盐
        map.put("userToken", sha(tenantNum + salt));// 加密后的密码
        return map;
    }

    /**
     * 生成随机盐
     * @return 随机生成的盐
     */
    private static String getSalt() {
        SecureRandom random = new SecureRandom();
        int length = random.nextInt(5) + 8;// 盐的长度，这里是8-12可自行调整
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = ra.charAt(random.nextInt(ra.length()));
        }
        return new String(text);
    }
}

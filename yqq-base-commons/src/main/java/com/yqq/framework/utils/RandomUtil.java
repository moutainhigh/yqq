/*
 * 文 件 名:  RandomUtil.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2018年7月30日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class RandomUtil {
	private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String NUMBER_CHAR = "0123456789";
    
	  /**
     * 获取定长的随机数，包含大小写、数字
     * @param length 随机数长度
     * @return
     */
    public static String generateString(int length) { 
        StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length()))); 
        } 
        return sb.toString(); 
    } 
    
    /**
     * 获取定长的随机数，包含大小写字母
     * @param length 随机数长度
     * @return
     */
    public static String generateMixString(int length) { 
        StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
                sb.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length()))); 
        } 
        return sb.toString(); 
    } 
    
    /**
     * 获取定长的随机数，只包含小写字母
     * @param length 随机数长度
     * @return
     */
    public static String generateLowerString(int length) { 
        return generateMixString(length).toLowerCase(); 
    } 
    
    /**
     * 获取定长的随机数，只包含大写字母
     * @param length 随机数长度
     * @return
     */
    public static String generateUpperString(int length) { 
        return generateMixString(length).toUpperCase(); 
    } 
    
    /**
     * 获取定长的随机数，只包含数字
     * @param length 随机数长度
     * @return
     */
    public static String generateNumberString(int length){
    	StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
            sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length()))); 
        } 
        return sb.toString(); 
    }
    /////////////////// 获取Random实例//////////////
    /**
    * 返回无锁的ThreadLocalRandom
    */
    public static Random threadLocalRandom() {
        return ThreadLocalRandom.current();
    }

    /**
    * 使用性能更好的SHA1PRNG, Tomcat的sessionId生成也用此算法.
    * 
    * 但JDK7中，需要在启动参数加入 -Djava.security=file:/dev/./urandom （中间那个点很重要）
    * 
    * 详见：《SecureRandom的江湖偏方与真实效果》http://calvin1978.blogcn.com/articles/securerandom.html
    */
    public static SecureRandom secureRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        }
        catch (NoSuchAlgorithmException e) {// NOSONAR
            return new SecureRandom();
        }
    }

    ////////////////// nextInt 相关/////////
    /**
    * 返回0到Intger.MAX_VALUE的随机Int, 使用ThreadLocalRandom.
    */
    public static int nextInt() {
        return nextInt(ThreadLocalRandom.current());
    }

    /**
    * 返回0到Intger.MAX_VALUE的随机Int, 可传入ThreadLocalRandom或SecureRandom
    */
    public static int nextInt(Random random) {
        int n = random.nextInt();
        if (n == Integer.MIN_VALUE) {
            n = 0; // corner case
        }
        else {
            n = Math.abs(n);
        }
        return n;
    }

    /**
    * 返回0到max的随机Int, 使用ThreadLocalRandom.
    */
    public static int nextInt(int max) {
        return nextInt(ThreadLocalRandom.current(), max);
    }

    /**
    * 返回0到max的随机Int, 可传入SecureRandom或ThreadLocalRandom
    */
    public static int nextInt(Random random, int max) {
        return random.nextInt(max);
    }

    /**
    * 返回min到max的随机Int, 使用ThreadLocalRandom.
    * 
    * min必须大于0.
    */
    public static int nextInt(int min, int max) {
        return nextInt(ThreadLocalRandom.current(), min, max);
    }

    /**
    * 返回min到max的随机Int,可传入SecureRandom或ThreadLocalRandom.
    * 
    * min必须大于0.
    * 
    * JDK本身不具有控制两端范围的nextInt，因此参考Commons Lang RandomUtils的实现, 不直接复用是因为要传入Random实例
    * 
    * @see org.apache.commons.lang3.RandomUtils nextInt(long, long)
    */
    public static int nextInt(Random random, int min, int max) {
        Validate.isTrue(max >= min, "Start value must be smaller or equal to end value.");
        com.yqq.framework.utils.MoreValidate.nonNegative("min", min);
        if (min == max) {
            return min;
        }
        return min + random.nextInt(max - min);
    }

    ////////////////// long 相关/////////
    /**
    * 返回0－Long.MAX_VALUE间的随机Long, 使用ThreadLocalRandom.
    */
    public static long nextLong() {
        return nextLong(ThreadLocalRandom.current());
    }

    /**
    * 返回0－Long.MAX_VALUE间的随机Long, 可传入SecureRandom或ThreadLocalRandom
    */
    public static long nextLong(Random random) {
        long n = random.nextLong();
        if (n == Long.MIN_VALUE) {
            n = 0; // corner case
        }
        else {
            n = Math.abs(n);
        }
        return n;
    }

    /**
    * 返回0－max间的随机Long, 使用ThreadLocalRandom.
    */
    public static long nextLong(long max) {
        return nextLong(ThreadLocalRandom.current(), 0, max);
    }

    /**
    * 返回0-max间的随机Long, 可传入SecureRandom或ThreadLocalRandom
    */
    public static long nextLong(Random random, long max) {
        return nextLong(random, 0, max);
    }

    /**
    * 返回min－max间的随机Long, 使用ThreadLocalRandom.
    * 
    * min必须大于0.
    */
    public static long nextLong(long min, long max) {
        return nextLong(ThreadLocalRandom.current(), min, max);
    }

    /**
    * 返回min-max间的随机Long,可传入SecureRandom或ThreadLocalRandom.
    * 
    * min必须大于0.
    * 
    * JDK本身不具有控制两端范围的nextLong，因此参考Commons Lang RandomUtils的实现, 不直接复用是因为要传入Random实例
    *
    * @see org.apache.commons.lang3.RandomUtils#nextLong(long, long)
    */
    public static long nextLong(Random random, long min, long max) {
        Validate.isTrue(max >= min, "Start value must be smaller or equal to end value.");
        com.yqq.framework.utils.MoreValidate.nonNegative("min", min);
        if (min == max) {
            return min;
        }
        return (long) (min + ((max - min) * random.nextDouble()));
    }

    ///////// Double //////
    /**
    * 返回0-之间的double, 使用ThreadLocalRandom
    */
    public static double nextDouble() {
        return nextDouble(ThreadLocalRandom.current(), 0, Double.MAX_VALUE);
    }

    /**
    * 返回0-Double.MAX之间的double
    */
    public static double nextDouble(Random random) {
        return nextDouble(random, 0, Double.MAX_VALUE);
    }

    /**
    * 返回0-max之间的double, 使用ThreadLocalRandom
    * 
    * 注意：与JDK默认返回0-1的行为不一致.
    */
    public static double nextDouble(double max) {
        return nextDouble(ThreadLocalRandom.current(), 0, max);
    }

    /**
    * 返回0-max之间的double
    */
    public static double nextDouble(Random random, double max) {
        return nextDouble(random, 0, max);
    }

    /**
    * 返回min-max之间的double,ThreadLocalRandom
    */
    public static double nextDouble(final double min, final double max) {
        return nextDouble(ThreadLocalRandom.current(), min, max);
    }

    /**
    * 返回min-max之间的double
    */
    public static double nextDouble(Random random, final double min, final double max) {
        Validate.isTrue(max >= min, "Start value must be smaller or equal to end value.");
        com.yqq.framework.utils.MoreValidate.nonNegative("min", min);
        if (Double.compare(min, max) == 0) {
            return min;
        }
        return min + ((max - min) * random.nextDouble());
    }
    //////////////////// String/////////

    /**
    * 随机字母或数字，固定长度
    */
    public static String randomStringFixLength(int length) {
        return RandomStringUtils.random(length, 0, 0, true, true, null, threadLocalRandom());
    }

    /**
    * 随机字母或数字，固定长度
    */
    public static String randomStringFixLength(Random random, int length) {
        return RandomStringUtils.random(length, 0, 0, true, true, null, random);
    }

    /**
    * 随机字母或数字，随机长度
    */
    public static String randomStringRandomLength(int minLength, int maxLength) {
        return RandomStringUtils.random(nextInt(minLength, maxLength), 0, 0, true, true, null, threadLocalRandom());
    }

    /**
    * 随机字母或数字，随机长度
    */
    public static String randomStringRandomLength(Random random, int minLength, int maxLength) {
        return RandomStringUtils.random(nextInt(random, minLength, maxLength), 0, 0, true, true, null, random);
    }

    /**
    * 随机字母，固定长度
    */
    public static String randomLetterFixLength(int length) {
        return RandomStringUtils.random(length, 0, 0, true, false, null, threadLocalRandom());
    }

    /**
    * 随机字母，固定长度
    */
    public static String randomLetterFixLength(Random random, int length) {
        return RandomStringUtils.random(length, 0, 0, true, false, null, random);
    }

    /**
    * 随机字母，随机长度
    */
    public static String randomLetterRandomLength(int minLength, int maxLength) {
        return RandomStringUtils.random(nextInt(minLength, maxLength), 0, 0, true, false, null, threadLocalRandom());
    }

    /**
    * 随机字母，随机长度
    */
    public static String randomLetterRandomLength(Random random, int minLength, int maxLength) {
        return RandomStringUtils.random(nextInt(random, minLength, maxLength), 0, 0, true, false, null, random);
    }

    /**
    * 随机ASCII字符(含字母，数字及其他符号)，固定长度
    */
    public static String randomAsciiFixLength(int length) {
        return RandomStringUtils.random(length, 32, 127, false, false, null, threadLocalRandom());
    }

    /**
    * 随机ASCII字符(含字母，数字及其他符号)，固定长度
    */
    public static String randomAsciiFixLength(Random random, int length) {
        return RandomStringUtils.random(length, 32, 127, false, false, null, random);
    }

    /**
    * 随机ASCII字符(含字母，数字及其他符号)，随机长度
    */
    public static String randomAsciiRandomLength(int minLength, int maxLength) {
        return RandomStringUtils.random(nextInt(minLength, maxLength), 32, 127, false, false, null,
                threadLocalRandom());
    }

    /**
    * 随机ASCII字符(含字母，数字及其他符号)，随机长度
    */
    public static String randomAsciiRandomLength(Random random, int minLength, int maxLength) {
        return RandomStringUtils.random(nextInt(random, minLength, maxLength), 32, 127, false, false, null, random);
    }
    
    
    public static String randomTwNum() {
    	Calendar c=Calendar.getInstance();
	    String time=new SimpleDateFormat("yyyy-MM-ddHHmmss").format(c.getTime()).toString();
	    StringBuffer s=new StringBuffer(time.substring(14, 16));
	    Long sys=System.currentTimeMillis();
	    s.append(sys.toString().substring(11, 13));
	    Double tm=Math.random()*10000000+1;
	    s.append(tm.toString().substring(tm.toString().length()-8, tm.toString().length()));
	    return s.toString();
    }
}

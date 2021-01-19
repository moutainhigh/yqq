/*
 * 文 件 名:  MoreValidate.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2018年7月30日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class MoreValidate {
    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static int positive(@Nullable String role, int x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static Integer positive(@Nullable String role, Integer x) {
        if (x.intValue() <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static long positive(@Nullable String role, long x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static Long positive(@Nullable String role, Long x) {
        if (x.longValue() <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static double positive(@Nullable String role, double x) {
        if (!(x > 0)) { // not x < 0, to work with NaN.
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static int nonNegative(@Nullable String role, int x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static Integer nonNegative(@Nullable String role, Integer x) {
        if (x.intValue() < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static long nonNegative(@Nullable String role, long x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static Long nonNegative(@Nullable String role, Long x) {
        if (x.longValue() < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static double nonNegative(@Nullable String role, double x) {
        if (!(x >= 0)) { // not x < 0, to work with NaN.
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }
}

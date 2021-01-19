/*
 * 文 件 名:  TvMemberTypeEnum.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2020年3月3日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.enums;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public enum TvMemberTypeEnum {

    YDZXHY_10Y("ZXHY10"),
    YDZXHY_20Y("ZXHY20");

    private String numVal;

    TvMemberTypeEnum(String numVal) {
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

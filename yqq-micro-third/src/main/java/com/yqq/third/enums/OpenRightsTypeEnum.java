/*
 * 文 件 名:  OpenRightsTypeEnum.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2020年3月4日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.enums;

/**
 * 开通权益接口类型
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public enum OpenRightsTypeEnum {
    /**
     * 无对应权益，匹配不到时返回该类型，业务上直接错误返回
     */
    R_NULL,
    /**
     * 视频会员
     */
    R_TV,
    /**
     * 商城购机
     */
    R_MALL,
    /**
     * 折扣
     */
    R_DISCOUNT,
    /**
     * 异业券
     */
    R_FOREIGN,
    /**
     * 营销案
     */
    R_MARKET,
    /**
     * 通用券
     */
    R_UNIVERSAL,
    /**
     * 互联网中心权益
     */
    R_INTERNET,
    /**
     * 美团
     */
    R_MT
    ;

}

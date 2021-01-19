/*
 * 文 件 名:  InternetCService.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2020年3月11日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.service;

import com.yqq.framework.web.result.JSONResult;
import com.yqq.third.model.UserInfoBean;

/**
 * 互联网中心接口
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public interface InternetCService {
    
    /**
     * <pre>
     * 订单查询
     * 1 当参数传 channelNo is not null 其他参数失效
     * 2 当参数传 channelNo is null  用户信息不能为空，salesId、status 参数可为空
     * </pre>
     * @param userInfoBean 当前用户信息
     * @param channelNo 查询订单号  我方下单时订单号
     * @param salesId 查询指定业务编码 
     * @param status 业务状态  0-未处理，1-待支付，2-支付异常，3-待开通，4-开通异常 ，5-订单完成
     * @return
     */
    public JSONResult<Object> queryOrders(UserInfoBean userInfoBean, String channelNo, String salesId, String status);

    /**
     * <pre>
     * 权益预开通查询接口
     * </pre>
     * @param userInfoBean 当前用户信息
     * @param salesId 查询指定业务编码 
     * @param prodId 产品编码
     * @return queryProdResultCode 0表示可以开通，1表示不能开通，        2表示重复开通，3表示业务互斥
     */
    public JSONResult<Object> queryIsOpenRights(UserInfoBean userInfoBean, String salesId, String prodId);
}

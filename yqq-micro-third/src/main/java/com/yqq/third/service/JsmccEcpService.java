/*
 * 文 件 名:  EcpService.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2020年3月2日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.service;

import com.yqq.third.model.UserInfoBean;

/**
 * 江苏权益接口
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public interface JsmccEcpService {
    
    /**
     * 
     * 查询用户是否开通会员
     */
    public boolean isVip(UserInfoBean userInfoBean);
    
    /**
     * 
     * 查询会员生日
     * @param userInfoBean
     */
    public String queryMemberBirthday(UserInfoBean userInfoBean);
}

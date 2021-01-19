/*
 * 文 件 名:  OpenRightsService.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2020年3月4日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.service;

import com.yqq.commons.model.RightsHandleResult;
import com.yqq.third.enums.OpenRightsTypeEnum;
import com.yqq.third.model.RightsParam;
import com.yqq.third.model.request.wap.RightsLeadReq;

import java.util.List;

/**
 * 权益开通接口
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public interface OpenRightsService {
    
    /**
     * 
     * 获取实现接口类型枚举值
     * @return
     */
    public OpenRightsTypeEnum getTypeEnum();
    
    /**
     * 
     * 开通权益
     * @param rightsLeadReq 包含 userInfoBean 办理用户信息,办理参数
     * @return
     */
    public RightsHandleResult openRights(RightsLeadReq rightsLeadReq, List<RightsParam> rightsParams);
}

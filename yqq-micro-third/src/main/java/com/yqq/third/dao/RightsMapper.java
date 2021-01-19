/*
 * 文 件 名:  RightsMapper.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator 工号
 * 修改时间:  2020年3月3日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.dao;

import com.yqq.third.model.RightsParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <权益数据库操作>
 * 
 * @author jiangtao 1806
 * @see [相关类/方法]
 */
@Mapper
public interface RightsMapper {

    /**
     * 
     * 根据权益编码获取接入编码
     * 
     * @param rightsNum
     *            权益编码
     * @return
     */
    String getRightsJoinNum(@Param(value = "rightsNum") String rightsNum);

    /**
     * 
     * <权益对接参数>
     * 
     * @param rightsNum
     *            权益编码
     * @return
     */
    List<RightsParam> queryRightsParamList(@Param(value = "rightsNum") String rightsNum);

}

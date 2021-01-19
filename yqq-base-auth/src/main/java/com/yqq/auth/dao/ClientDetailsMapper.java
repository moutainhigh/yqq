/*
 * 文 件 名:  TestUserMapper.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年1月16日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.auth.dao;

import com.yqq.auth.model.ClientDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <功能描述>
 * 
 * @author  yangcuan
 * @see  [相关类/方法]
 */
@Mapper
public interface ClientDetailsMapper {
    /**
     * 
     * 根据appid 查询 客户端信息
     * @param appId
     * @return
     */
    ClientDetail getClientDetailByAppId(@Param("appId")String appId);
}

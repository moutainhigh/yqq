package com.yqq.third.dao;

import com.yqq.third.model.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    /**
     * 获取用户信息
     * @param mobie
     * @return
     */
    UserInfoBean getUserBaseInfo(String mobie);


    int insertUserBaseInfo(UserInfoBean userInfoBean);
}

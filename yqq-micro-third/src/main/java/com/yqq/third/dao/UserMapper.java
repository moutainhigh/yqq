package com.yqq.third.dao;

import com.yqq.third.model.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    /**
     * 检索手机唯一性
     * @param mobile
     * @return
     */
    UserInfoBean getUserBaseInfoM(String mobile);

    /**
     * 检索账号唯一性
     * @param account
     * @return
     */
    UserInfoBean getUserBaseInfoA(String account);


    int insertUserBaseInfo(UserInfoBean userInfoBean);
}

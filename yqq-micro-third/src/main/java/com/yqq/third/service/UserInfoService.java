package com.yqq.third.service;

import com.yqq.third.model.UserInfoBean;

public interface UserInfoService {
    /**
     * 查询是否存在用户
     * @param mobile
     * @return
     */
    UserInfoBean getUserBaseInfoM(String mobile);

    UserInfoBean getUserBaseInfoA(String account);

    /**
     * 添加用户基本信息
     * @param userInfoBean
     * @return
     */
    int insertUserBaseInfo(UserInfoBean userInfoBean);
}

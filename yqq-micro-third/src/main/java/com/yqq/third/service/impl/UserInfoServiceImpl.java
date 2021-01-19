package com.yqq.third.service.impl;

import com.yqq.third.dao.UserMapper;
import com.yqq.third.model.UserInfoBean;
import com.yqq.third.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl  implements UserInfoService {
    @Autowired
   private UserMapper userMapper;

    @Override
    public UserInfoBean getUserBaseInfo(String mobile) {
        return userMapper.getUserBaseInfo(mobile);
    }

    @Override
    public int insertUserBaseInfo(UserInfoBean userInfoBean) {
        return userMapper.insertUserBaseInfo(userInfoBean);
    }
}

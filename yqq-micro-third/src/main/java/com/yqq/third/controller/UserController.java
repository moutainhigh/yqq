package com.yqq.third.controller;

import com.yqq.framework.utils.DateTimeUtils;
import com.yqq.framework.web.result.JSONResult;
import com.yqq.third.model.UserInfoBean;
import com.yqq.third.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户相关接口
 *
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Api(value = "user", tags = "用户接口")
@RequestMapping("user")
@RestController
@CrossOrigin
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfoService userInfoService;


    @ApiOperation(value = "用户注册接口", notes = "用户注册接口", tags = "USER_REGIST_THIRD")
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mobile", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码", dataType = "String")})
    public JSONResult registUser(String mobile,String pwd){

        JSONResult jsonData = new JSONResult<>();
        logger.info("mobile="+StringUtils.isNotBlank(mobile));
        logger.info("pwd="+StringUtils.isNotBlank(pwd));
        if(!StringUtils.isNotBlank(mobile)){
            jsonData.setFailInfo("手机号不能为空");
            return jsonData;
        }else if(!StringUtils.isNotBlank(pwd)){
            jsonData.setFailInfo("密码不能为空");
            return jsonData;
        }else {
            try {
                UserInfoBean userInfoBean = userInfoService.getUserBaseInfo(mobile);
                if (userInfoBean == null) {
                    userInfoBean =new UserInfoBean();
                    userInfoBean.setMobile(mobile);
                    userInfoBean.setPwd(pwd);
                    userInfoBean.setRegistTime(DateTimeUtils.getTodayChar14());
                    userInfoService.insertUserBaseInfo(userInfoBean);
                    jsonData.setSuccessInfo("注册成功!");

                } else {
                    jsonData.setFailInfo("用户已存在，无法注册");
                }
                return null;
            } catch (Exception e) {
                logger.error("user regist", e);
                e.printStackTrace();
                jsonData.setFailInfo("系统异常，请稍后再试！");
            }
            return jsonData;
        }
    }
}

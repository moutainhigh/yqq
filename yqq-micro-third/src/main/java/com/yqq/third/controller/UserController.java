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
                         @ApiImplicitParam(name = "account", value = "账号", dataType = "String"),
                         @ApiImplicitParam(name = "vcode", value = "验证码", dataType = "String"),
                         @ApiImplicitParam(name = "pwd", value = "密码", dataType = "String"),
    })
    public JSONResult registUser(String mobile,String account,String vcode,String pwd){

        JSONResult jsonData = new JSONResult<>();
        logger.info("mobile="+StringUtils.isNotBlank(mobile));
        logger.info("pwd="+StringUtils.isNotBlank(pwd));
        if(StringUtils.isBlank(mobile)){
            jsonData.setFailInfo("手机号不能为空");
            return jsonData;
        }else if(StringUtils.isBlank(pwd)){
            jsonData.setFailInfo("密码不能为空");
            return jsonData;
        }else if(StringUtils.isBlank(vcode)){
            jsonData.setFailInfo("验证码为空");
            return jsonData;
        }else if(StringUtils.isBlank(account)){
            jsonData.setFailInfo("账号为空");
            return jsonData;
        }
        else{

            try {
                //验证码校验


                    UserInfoBean userInfoBeanM = userInfoService.getUserBaseInfoM(mobile);
                     UserInfoBean userInfoBeanA = userInfoService.getUserBaseInfoA(account);
                    if(userInfoBeanM!=null){
                        jsonData.setFailInfo("该号码已注册");
                        return jsonData;
                    }
                    if(userInfoBeanA!=null){
                        jsonData.setFailInfo("该账号已注册");
                        return jsonData;
                    }else {

                        UserInfoBean userInfoBean = new UserInfoBean();
                        userInfoBean.setMobile(mobile);
                        userInfoBean.setAccount(account);
                        userInfoBean.setPwd(pwd);
                        userInfoBean.setRegistTime(DateTimeUtils.getTodayChar14());
                        userInfoService.insertUserBaseInfo(userInfoBean);
                        jsonData.setSuccessInfo("注册成功!");
                    }



            } catch (Exception e) {
                logger.error("user regist", e);
                e.printStackTrace();
                jsonData.setFailInfo("系统异常，请稍后再试！");
            }
            return jsonData;
        }
    }

    @ApiOperation(value = "短信获取接口", notes = "短信获取接口", tags = "VCODE_THIRD")
    @ApiImplicitParams({ @ApiImplicitParam(name = "mobile", value = "手机号码", dataType = "String")})
    @RequestMapping(value = "getVcode", method = RequestMethod.POST)
    public JSONResult getSMSVocde(String mobile){
        JSONResult jsonData = new JSONResult<>();

        if(StringUtils.isBlank(mobile)) {
            jsonData.setFailInfo("手机号为空！");
            return  jsonData;
        }
        //调用第三方API发送短信验证码逻辑



        return  jsonData;
    }

    @ApiOperation(value = "用户登录接口", notes = "用户登录接口", tags = "USER_LOGIN_THIRD")
    @RequestMapping(value = "login", method = RequestMethod.POST)

    @ApiImplicitParams({ @ApiImplicitParam(name = "mobile", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(name = "account", value = "账号", dataType = "String"),
            @ApiImplicitParam(name = "vcode", value = "验证码", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "登录方式", dataType = "String")
    })
    public JSONResult login(String mobile,String account,String vcode,String pwd,String type){
        JSONResult jsonData = new JSONResult<>();
        if(StringUtils.isBlank(type)){
            jsonData.setFailInfo("登录方式未选择！");
            return jsonData;
        }
        //账号密码登录
        if("1".equals(type)){

        }
        return  jsonData;
    }


}

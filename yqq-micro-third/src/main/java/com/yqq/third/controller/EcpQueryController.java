///*
// * 文 件 名:  EcpQueryController.java
// * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
// * 描    述:  <描述>
// * 修 改 人:  lib 工号
// * 修改时间:  2020年4月26日
// * 跟踪单号:  <跟踪单号>
// * 修改单号:  <修改单号>
// * 修改内容:  <修改内容>
// */
//package com.yqq.third.controller;
//
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.service.JsmccEcpService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * ecp 查询接口  接口废弃
// *
// * @author  lib 工号
// * @see  [相关类/方法]
// */
//@Api(value = "ecp query ", tags = "ecp 查询接口  接口废弃")
//@RestController
//@RequestMapping("ecp")
//@Deprecated
//public class EcpQueryController {
//
//    @Autowired
//    private JsmccEcpService jsmccEcpService;
//
//    @ApiOperation(value = "查询用户是否开通会员测试接口", notes = "查询用户是否开通会员测试接口")
//    @RequestMapping( value= "isVipUser", method = RequestMethod.POST)
//    @ResponseBody
//    public Boolean isVipUser(UserInfoBean userInfoBean) {
//        boolean isVip = jsmccEcpService.isVip(userInfoBean);
//        return isVip;
//    }
//
//    @ApiOperation(value = "查询用户是否开通会员测试接口", notes = "查询用户是否开通会员测试接口")
//    @RequestMapping(value= "queryMemberBirthday", method = RequestMethod.POST)
//    @ResponseBody
//    public String queryMemberBirthday(UserInfoBean userInfoBean) {
//        String birthday = jsmccEcpService.queryMemberBirthday(userInfoBean);
//        return birthday;
//    }
//}

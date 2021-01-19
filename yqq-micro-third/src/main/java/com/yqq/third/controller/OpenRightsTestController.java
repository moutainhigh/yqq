///*
// * 文 件 名:  TestController.java
// * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
// * 描    述:  <描述>
// * 修 改 人:  yangchuan
// * 修改时间:  2020年3月4日
// * 跟踪单号:  <跟踪单号>
// * 修改单号:  <修改单号>
// * 修改内容:  <修改内容>
// */
//package com.yqq.third.controller;
//
//import com.yqq.commons.model.RightsHandleResult;
//import com.yqq.framework.web.result.JSONResult;
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.model.request.wap.RightsLeadReq;
//import com.yqq.third.service.InternetCService;
//import com.yqq.third.service.JsmccEcpService;
//import com.yqq.third.service.OpenRightsServiceFactory;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 权益开通测试接口
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Api(value = "openrights", tags = "权益开通测试接口")
//@RequestMapping("openrights")
//@RestController
//public class OpenRightsTestController {
//
//    @Autowired
//    private OpenRightsServiceFactory openRightsServiceFactory;
//
//    @Autowired
//    private JsmccEcpService jsmccEcpService;
//
//    @Autowired
//    private InternetCService internetCService;
//
//    @ApiOperation(value = "权益开通测试接口", notes = "权益开通测试接口")
//    @RequestMapping(value =  "test", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONResult<List<RightsHandleResult>> test() {
//        RightsLeadReq rightsLeadReq = new RightsLeadReq();
//        rightsLeadReq.setUserInfoBean(get13801432424UserBean());
//        List<RightsLeadReq> list = new ArrayList<>();
//        list.add(rightsLeadReq);
//        JSONResult<List<RightsHandleResult>> result = openRightsServiceFactory.openRights(list);
//        return result;
//    }
//
//    @ApiOperation(value = "查询用户是否开通会员测试接口", notes = "查询用户是否开通会员测试接口")
//    @RequestMapping( value= "testIsVip", method = RequestMethod.GET)
//    @ResponseBody
//    public Boolean testIsVip() {
//        UserInfoBean userInfoBean = get13801432424UserBean();
//        boolean isVip = jsmccEcpService.isVip(userInfoBean);
//        return isVip;
//    }
//
//
//    @ApiOperation(value = "查询用户是否开通会员测试接口", notes = "查询用户是否开通会员测试接口")
//    @RequestMapping(value= "queryMemberBirthday", method = RequestMethod.GET)
//    @ResponseBody
//    public String queryMemberBirthday() {
//        UserInfoBean userInfoBean = get13801432424UserBean();
//        String birthday = jsmccEcpService.queryMemberBirthday(userInfoBean);
//        return birthday;
//    }
//
//    @ApiOperation(value = "互联网中心-订单状态查询接口", notes = "互联网中心-订单状态查询接口")
//    @RequestMapping(value = "queryOrders" , method = RequestMethod.GET)
//    @ResponseBody
//    public JSONResult<Object> queryOrders() {
//        JSONResult<Object> result = internetCService.queryOrders(get13801432424UserBean(), "45500cb9-8681-42ab-a73f-8a30dd52c495", "", "");
//        return result;
//    }
//
//
//    @ApiOperation(value = "互联网中心-权益预开通查询接口", notes = "互联网中心权益预开通查询接口")
//    @RequestMapping(value = "queryIsOpenRights" , method = RequestMethod.GET)
//    @ResponseBody
//    public JSONResult<Object> queryIsOpenRights() {
//        JSONResult<Object> result = internetCService.queryIsOpenRights(get13801432424UserBean(), "101972", "6000642");
//        return result;
//    }
//
//    public static UserInfoBean get13801432424UserBean() {
//        UserInfoBean user = new UserInfoBean();
//        user.setApplyDate("20181011000000");
//        user.setBrandNum("1");
//        user.setCityNo("21");
//        user.setCountryNo("2157");
//        user.setCustIcType("1");
//        user.setIs4GUser("1");
//        user.setLoginSource(3);
//        user.setLoginType("3");
//        user.setMobile("13801432424");
//        user.setSessionId("a5a-Kjqv2JdiZs8RosQE1jxOK_Wszhe09wncASDuJNhblzxKF3Y9!558165609");
//        user.setSsoActiveTime("201909271527");
//        user.setSsoCookieToken("76887CED2BC94C5FB27DC55554B93763@js.ac.10086.cn");
//        user.setUserId("2157200003124214");
//        user.setUserState("1");
//        user.setUserpayMode("2");
//        user.setRemoteAddr("218.94.54.84");
//        return user;
//    }
//}

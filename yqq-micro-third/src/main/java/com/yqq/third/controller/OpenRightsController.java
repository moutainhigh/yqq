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
//import com.yqq.third.model.request.wap.RightsLeadReq;
//import com.yqq.third.service.MallRightsServiceFactory;
//import com.yqq.third.service.OpenRightsServiceFactory;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
///**
// * 第三方权益开通接口
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Api(value = "open rights", tags = "第三方权益开通接口")
//@RequestMapping("rights")
//@RestController
//public class OpenRightsController {
//
//    @Autowired
//    private OpenRightsServiceFactory openRightsServiceFactory;
//
//    @Autowired
//    private MallRightsServiceFactory mallRightsServiceFactory;
//
//    @ApiOperation(value = "权益开通接口", notes = "权益开通接口")
//    @RequestMapping(value = "open", method = RequestMethod.POST)
//    @ResponseBody
//    public JSONResult<List<RightsHandleResult>> openRights(@RequestBody @Valid List<RightsLeadReq> rightsLeadReqs) {
//        JSONResult<List<RightsHandleResult>> result = openRightsServiceFactory.openRights(rightsLeadReqs);
//        return result;
//    }
//
//    @ApiOperation(value = "权益开通接口", notes = "权益开通接口")
//    @RequestMapping(value = "open/mall", method = RequestMethod.POST)
//    @ResponseBody
//    public JSONResult<String> openMallRights(@RequestBody @Valid RightsLeadReq rightsLeadReq) {
//        JSONResult<String> result = mallRightsServiceFactory.openRights(rightsLeadReq);
//        return result;
//    }
//}

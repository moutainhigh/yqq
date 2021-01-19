///*
// * 文 件 名:  JsmccEcpServiceImpl.java
// * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
// * 描    述:  <描述>
// * 修 改 人:  yangchuan
// * 修改时间:  2020年3月2日
// * 跟踪单号:  <跟踪单号>
// * 修改单号:  <修改单号>
// * 修改内容:  <修改内容>
// */
//package com.yqq.third.service.impl;
//
//import com.yqq.commons.service.BaseService;
//import com.yqq.framework.utils.JacksonUtils;
//import com.yqq.third.enums.VipTypeEnum;
//import com.yqq.third.model.UserInfoBean;
//import com.yqq.third.service.JsmccEcpService;
//import com.yqq.third.utils.BaseEcp;
//import com.yqq.third.utils.ECPUtils;
//import com.yqqh.xwecp.service.logic.LIException;
//import com.yqqh.xwecp.service.logic.client_impl.common.IQueryBusinessService;
//import com.yqqh.xwecp.service.logic.client_impl.common.IQueryUserService;
//import com.yqqh.xwecp.service.logic.client_impl.common.impl.QueryBusinessServiceClientImpl;
//import com.yqqh.xwecp.service.logic.client_impl.common.impl.QueryUserServiceClientImpl;
//import com.yqqh.xwecp.service.logic.pojo.GommonBusiness;
//import com.yqqh.xwecp.service.logic.pojo.QRY020001Result;
//import com.yqqh.xwecp.service.logic.pojo.QRY040129Result;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 江苏ecp接口实现
// *
// * @author  yangchuan
// * @see  [相关类/方法]
// */
//@Service(value="jsmccEcpService")
//public class JsmccEcpServiceImpl extends BaseService implements JsmccEcpService {
//
//    private IQueryUserService iQueryUserService = new QueryUserServiceClientImpl();
//
//    private IQueryBusinessService iQueryBusinessService = new QueryBusinessServiceClientImpl();
//
//    @Override
//    public boolean isVip(UserInfoBean userInfoBean) {
//        // 初始化ECP上下文
//        BaseEcp.generateContextForBusi("jsmcc-rights-001", "4", userInfoBean);
//        boolean isVip = false;
//        try {
//            for(VipTypeEnum str: VipTypeEnum.values()) {
//                String code = str.toString();
//                QRY020001Result result = iQueryBusinessService.queryBusiness(userInfoBean.getMobile(), 2, code);
//                ilogger.info("ecp\u0001queryBusiness\u0001"+ userInfoBean.getMobile() + "\u0001\u0001" + JacksonUtils.toJSon(result));
//                if (ECPUtils.isSuccess(result)) {
//                    List<GommonBusiness> gommonBusinessList1 = result.getGommonBusiness();
//                    for (GommonBusiness business : gommonBusinessList1) {
//                        int state = business.getState();
//                        logger.info(" {} 用户是否开通会员查询  code {} 返回状态 state {}", userInfoBean.getMobile(), code, state);
//                        if (1 != state) {
//                            isVip = true;// 已开通
//                            break;
//                        }
//                    }
//                }
//                if(isVip) {
//                    break;
//                }
//            }
//            return isVip;
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("QueryBusinessServiceClientImpl queryBusiness 错误  mobile {} exception {} ", userInfoBean.getMobile(), e);
//        }
//        return isVip;
//    }
//
//    @Override
//    public String queryMemberBirthday(UserInfoBean userInfoBean) {
//        BaseEcp.generateContextForBusi("jsmcc-rights-001","4",userInfoBean);
//        String birthMon = "";
//        try {
//            QRY040129Result qry040129Result = iQueryUserService.queryUser(userInfoBean.getMobile());
//            ilogger.info("ecp\u0001queryUser\u0001"+ userInfoBean.getMobile() + "\u0001\u0001" + JacksonUtils.toJSon(qry040129Result));
//            String retBirthMon = qry040129Result.getIcNo();
//            if(StringUtils.isNoneEmpty(retBirthMon)) {
//                if (retBirthMon.length() >= 15) {
//                    birthMon = retBirthMon.substring(10, 12);
//                }
//            }
//            logger.info("queryMemberBirthday 错误 mobile {} result {} ", userInfoBean.getMobile(), retBirthMon);
//        } catch (LIException e) {
//            e.printStackTrace();
//            logger.error("queryMemberBirthday 错误 mobile {} exception {}",userInfoBean.getMobile(), e);
//        }
//        return birthMon;
//    }
//
//}

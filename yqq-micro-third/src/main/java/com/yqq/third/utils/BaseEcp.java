//package com.yqq.third.utils;
//
//import com.yqq.third.model.UserInfoBean;
//import com.yqqh.xwecp.msg.InvocationContext;
//import com.yqqh.xwecp.service.logic.client.LIInvocationContext;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @Desc ECP调用基础类
// * @Author swy
// * @Date: 2018年6月21日 15:07:41
// */
//public class BaseEcp {
//
//    /**
//     *
//     * 接口上下文
//     * @param actCode
//     * @param opType
//     * @param user
//     */
//    public static void generateContextForBusi(String actCode, String opType, UserInfoBean user) {
//        LIInvocationContext lic = LIInvocationContext.getContext();
//        // ECP实例名，传递给ECP侧，用于区分网掌渠道
//        lic.setEcpInstance(ECPUtils.getEcpInstanceName());
//        if (user != null) {
//            String userBrand = user.getBrandNum();
//            String userCity = user.getCityNo();
//            String mobile = user.getMobile();
//            Map<String, Object> context = makeContextValueMap(user);
//            lic.setBizCode(actCode);
//            lic.setOpType(opType);
//            lic.setUserBrand(userBrand);
//            lic.setUserCity(userCity);
//            lic.setUserMobile(mobile);
//            if (context != null && context.size() > 0) {
//                InvocationContext ic = new InvocationContext();
//                Set<String> set = context.keySet();
//                Iterator<String> it = set.iterator();
//                while (it.hasNext()) {
//                    String key = (String) it.next();
//                    Object value = context.get(key);
//                    ic.addContextParameter(key, value);
//                }
//                ic.addContextParameter("loginiplock_login_ip", user.getRemoteAddr());
//                lic.setContextParameter(ic);
//            }
//        }
//    }
//
//    protected static Map<String, Object> makeContextValueMap(UserInfoBean user) {
//        UserInfoBean uib = user;
//        String mobile = uib.getMobile();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("route_type", "1");
//        map.put("route_value", uib.getCityNo());
//        map.put("login_msisdn", mobile);
//        map.put("ddr_city", uib.getCityNo());
//        map.put("user_id", uib.getUserId());
//        map.put("user_change_remark", "");
//        map.put("user_pwd", user.getPwd());
//        map.put("user_passwd", user.getPwd());
//        map.put("brand", uib.getBrandNum());
//        map.put("country", uib.getCountryNo());
//        // add end
//       return map;
//    }
//
//}

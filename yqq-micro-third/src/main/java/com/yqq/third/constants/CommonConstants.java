package com.yqq.third.constants;

/**
 * 公共参数
 *
 * @author yangchuan
 * @see [相关类/方法]
 */
public interface CommonConstants {

    /**
     * 渠道标识
     *
     * @author niuzf
     */
    interface Channel {
        /**
         * 网厅
         */
        String WEB = "web";
        /**
         * 掌厅
         */
        String WAP = "wap";
    }

    /**
     * 小渠道
     *
     * @author niuzf
     */
    interface SubChannel {
        /**
         * IOS客户端标版
         */
        String IPHONE_STAND_APP = "isapp";
        /**
         * ANDROID客户端标版
         */
        String ANDROID_STAND_APP = "asapp";
        /**
         * 苹果校园版APP
         */
        String IPHONE_SCHOOL_APP = "icapp";
        /**
         * 安卓校园版APP
         */
        String ANDROID_SCHOOL_APP = "acapp";
        /**
         * 微信
         */
        String WECHAT = "wechat";
        /**
         * 支付宝
         */
        String ALIPAY = "alipay";
        /**
         * 第三方浏览器
         */
        String OTHER = "other";
    }

    /**
     * 网络模式
     *
     * @author niuzf
     */
    interface NET_MODE {
        /**
         * 2G
         */
        String NET_2G = "2G";
        /**
         * 3G
         */
        String NET_3G = "3G";
        /**
         * 4G
         */
        String NET_4G = "4G";
        /**
         * WIFI
         */
        String WIFI = "WIFI";
        /**
         * 未知
         */
        String UNKNOWN = "UKN";
    }

    /**
     * 登陆标识
     */
    interface LoginType {
        /**
         * 服务密码登陆-SSO侧
         */
        int LOGIN_PWD_SSO = 1;
        /**
         * SSO短信密码登陆
         */
        int LOGIN_SMS_SSO = 2;
        /**
         * 安全认证登陆 -SSO侧
         */
        int LOGIN_AUTH_SSO = 3;
        /**
         * 服务密码登陆-ECP侧
         */
        int LOGIN_PWD_ECP = 4;
        /**
         * 网关前传登陆
         */
        int LOGIN_CMWAP = 5;
        /**
         * APP同步登陆
         */
        int LOGIN_APP = 6;
    }

    /**
     * 操作类型
     */
    interface ECPOpType {
        // 0登录
        String LOGIN = "0";
        // 1开通
        String OPEN = "1";
        // 2关闭
        String CLOSE = "2";
        // 3查询
        String QUERY = "3";
        // 4变更
        String CHANGE = "4";
        // 5其它
        String OTHER = "5";
        // 6校验
        String CHECK = "6";
    }

    /**
     * 加锁相关
     *
     * @author niuzf
     */
    interface LockConstants {
        // 加锁成功
        String LOCK_SUCCESS = "1";

        // 加锁失败
        String LOCK_FAIL = "-1";

        // 默认的超时时间设置为1分钟
        int LOCK_OVER_TIME = 60 * 1000;
    }

    /**
     * 活动自身平台操作类型
     *
     * @author niuzf
     */
    interface ACTOpType {
        // 0其它
        String OTHER = "0";
        // 1初始化
        String INIT = "1";
        // 2抽奖
        String DRAW = "2";
        // 3领奖
        String AWARD = "3";
        // 4中奖查询
        String QUERY_WIN = "4";
        // 5普通类查询
        String QUERY_COM = "5";
    }

    /**
     * 文件类型
     */
    interface FileType {
        /**
         * HTML文件
         */
        String HTML_FORMAT = ".html";
        /**
         * 图片格式为jpg
         */
        String IMG_FORMAT_JPEG = ".jpg";
        /**
         * 图片格式为jpg
         */
        String IMG_FORMAT_PNG = ".png";
        /**
         * 图片格式为jpg
         */
        String IMG_FORMAT_GIF = ".gif";
        /**
         * 样式文件
         */
        String CSS_FORMAT = ".css";
        /**
         * js脚本文件
         */
        String JS_FORMAT = ".js";
    }

    /**
     * 权益商户合同照片上传路径
     */
    public static final String OWNER_CONTRACT_IMG = "/web/upload/owner/contract/";

    /**
     * 权益照片上传路径
     */
    public static final String RIGHTS_IMG = "/web/upload/rights/";
    
    /**
     * 权益采购合同照片上传路径
     */
    public static final String RIGHTS_STOCK_IMG = "/web/upload/rights/stock/contract/";

}

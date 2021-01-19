package com.yqq.third.model;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value="商户信息")
public class MerchantBean implements Serializable {
    /**商户ID*/
    private String merchantId;
    /**手机号码*/
    private String mobile;
    /**密码*/
    private String pwd;
    /**商户状态 1：正常，0:关闭，-1：删除*/
    private String merchantState;
    /**注册时间*/
    private String registTime;
//    /**球场名*/
//    private String filedName;
//    /**球场地址*/
//    private String fieldAddr;
//    /**所在省份*/
//    private String provice;
//    /**所在城市*/
//    private String city;
//    /**所在区域*/
//    private String area;


}

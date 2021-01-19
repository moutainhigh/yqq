/*
 * 文 件 名:  OpenRightsResult.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  lib 工号
 * 修改时间:  2020年4月27日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.commons.model;

import java.io.Serializable;

/**
 *  开通权益返回信息
 * 
 * @author  lib 工号
 * @see  [相关类/方法]
 */
public class RightsHandleResult implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3406537466142812332L;
    /**
     * 成功
     */
    public static final String SUCCESS = "SUCC";
    /**
     * 接口返回错误
     */
    public static final String FAIL = "FAIL";
    /**
     * 接口调用异常
     */
    public static final String EXCEPT = "EXCEPT";
    
    /**
     * 领取码
     */
    private String drawNum;
    
    /**
     * 办理状态
     */
    private String handleStatus;
    
    /**
     * 办理信息
     */
    private String handleMsg;
    
    public RightsHandleResult() {
        this.handleMsg= "办理成功";
        this.handleStatus = SUCCESS;
    }

    /**
     * 获取 drawNum
     * @return 返回 drawNum
     */
    public String getDrawNum() {
        return drawNum;
    }

    /**
     * 设置 drawNum
     * @param drawNum 对drawNum进行赋值
     */
    public void setDrawNum(String drawNum) {
        this.drawNum = drawNum;
    }

    /**
     * 获取 handleStatus
     * @return 返回 handleStatus
     */
    public String getHandleStatus() {
        return handleStatus;
    }

    /**
     * 设置 handleStatus
     * @param handleStatus 对handleStatus进行赋值
     */
    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    /**
     * 获取 handleMsg
     * @return 返回 handleMsg
     */
    public String getHandleMsg() {
        return handleMsg;
    }

    /**
     * 设置 handleMsg
     * @param handleMsg 对handleMsg进行赋值
     */
    public void setHandleMsg(String handleMsg) {
        this.handleMsg = handleMsg;
    }
}

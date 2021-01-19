/*
 * 文 件 名:  CustomDefaultWebSessionManager.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2018年10月16日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.shiro.client;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class CustomDefaultWebSessionManager extends DefaultWebSessionManager {
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        if(sessionId == null){
             return super.retrieveSession(sessionKey);
        }
        ServletRequest request = WebUtils.getRequest(sessionKey);
        Object sessionObj = request.getAttribute(sessionId.toString());
        if (sessionObj != null) {
            return (Session) sessionObj;
        }
        Session s = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), s);
        }
        return s;
    }
}

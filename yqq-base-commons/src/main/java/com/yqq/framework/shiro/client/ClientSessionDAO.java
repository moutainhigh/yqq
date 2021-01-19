package com.yqq.framework.shiro.client;

import java.io.Serializable;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class ClientSessionDAO extends CachingSessionDAO {
    
    @Autowired
    private com.yqq.framework.shiro.client.RemoteService remoteService;

    private String appKey;

    public void setRemoteService(com.yqq.framework.shiro.client.RemoteService remoteService) {

        this.remoteService = remoteService;
    }

    public void setAppKey(String appKey) {

        this.appKey = appKey;
    }


    @Override
    protected void doDelete(Session session) {

        remoteService.deleteSession(appKey, session);
    }

    @Override
    protected void doUpdate(Session session) {

        remoteService.updateSession(appKey, session);
    }


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = remoteService.createSession(appKey,session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(final Serializable sessionId) {
        return remoteService.getSession(appKey, sessionId);

    }
}
package com.yqq.framework.shiro.client;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.session.Session;

import com.yqq.framework.model.Resource;
import com.yqq.framework.model.TemplateIdMap;
import com.yqq.framework.shiro.model.AuthPermissionContext;

/**
 * 
 * <功能描述>
 * 
 * @author  libiao 工号
 * @see  [相关类/方法]
 */
public interface RemoteService {
    /**
     * 
     * <功能描述>
     * @param appKey
     * @param sessionId
     * @return
     */
    Session getSession(String appKey, Serializable sessionId);

    /**
     * 
     * <功能描述>
     * @param appKey
     * @param session
     * @return
     */
    Serializable createSession(String appKey, Session session);

    /**
     * 
     * <功能描述>
     * @param appKey
     * @param session
     */
    void updateSession(String appKey, Session session);

    /**
     * 
     * <功能描述>
     * @param appKey
     * @param session
     */
    void deleteSession(String appKey, Session session);

    /**
     * 
     * <功能描述>
     * @param appKey
     * @param username
     * @return
     */
    AuthPermissionContext getPermissions(String appKey, String username);

    /**
     * 根据应用ID查询参数列表
     * 
     * @param appNum
     *            应用ID
     * @return 系统参数
     */
    String queryParamterListByAppNum(String appNum);

    /**
     * 获取应用信息
     * @param appNum 应用ID
     * @return 应用信息
     */
    String getApplicationByAppNum(String appNum);

    /**
    * 
    * 根据当前应用编码获取所有菜单对应权限
    * @param appNum
    * @return
    */
    @Deprecated
    List<Resource> getResourcesByAppNum(String appNum);

    /**
     * 
     * <功能描述>
     * @param tenantNum 租户编码
     * @param trigerNum 节点编码
     * @return
     */
    TemplateIdMap getTemplateIdMap(String tenantNum, String trigerNum);

    /**
    * 
    * 根据请求url判断当前登录用户是否有权限访问
    * @param appNum
    * @param uri
    * @return
    */
    boolean isRoleAccess(String appNum, String uri, String currentRoleId);
}

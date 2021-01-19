package com.yqq.framework.shiro.client;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yqq.framework.model.ShiroUser;
import com.yqq.framework.shiro.model.AuthPermissionContext;

/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class ClientRealm extends AuthorizingRealm {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private com.yqq.framework.shiro.client.RemoteService remoteService;

    private String appKey;


    public void setRemoteService(com.yqq.framework.shiro.client.RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    public void setAppKey(String appKey) {

        this.appKey = appKey;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //远程获取权限

        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        logger.debug("远程获取权限:{}", shiroUser.loginName);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AuthPermissionContext context = remoteService.getPermissions(appKey, shiroUser.loginName);
        authorizationInfo.setRoles(context.getRoles());
        authorizationInfo.setStringPermissions(context.getPermissions());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //永远不会被调用--客户端无需登录,远程验证
        throw new UnsupportedOperationException("永远不会被调用");

    }

}

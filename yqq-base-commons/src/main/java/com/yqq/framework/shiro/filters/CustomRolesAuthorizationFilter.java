package com.yqq.framework.shiro.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yqq.framework.model.ShiroUser;
import com.yqq.framework.shiro.client.RemoteService;
import com.yqq.framework.utils.SpringUtils;


/**
 * zhangq
 * 角色判断拦截 ， 默认RolesAuthorizationFilter 角色判断为并且关系， 此拦截只需拥有其中任一角色即可
 */
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomRolesAuthorizationFilter.class);
    private static final Logger vlogger = LoggerFactory.getLogger("VisitLog");

    private String appNum;

    public CustomRolesAuthorizationFilter(String appNum) {
        this.appNum = appNum;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
        RemoteService remoteService = SpringUtils.getBean(RemoteService.class);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(req);
        Subject subject = getSubject(req, resp);
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        logger.debug("判定【{}】是否有当前用户角色访问权限。",httpServletRequest.getRequestURI());
        String uri = httpServletRequest.getRequestURI();
        String accept = httpServletRequest.getHeader("Accept");
        String xRequestedWith = httpServletRequest.getHeader("X-Requested-With");
        // ajax 登录即可访问
        if ("XMLHttpRequest".equalsIgnoreCase(xRequestedWith)
                || (accept != null && accept.contains("application/json"))) {
            logger.debug("is ajax【{}】", uri);
            vlogger.info(uri + "|" + shiroUser.getLoginName());
            return true;
        }
        vlogger.info(uri + "|" + shiroUser.getLoginName());
        
        String currentRoleId = shiroUser.getRoleId();
        return remoteService.isRoleAccess(appNum, uri, currentRoleId);
    }

}
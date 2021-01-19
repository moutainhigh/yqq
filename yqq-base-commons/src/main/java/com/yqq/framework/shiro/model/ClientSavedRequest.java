package com.yqq.framework.shiro.model;

import org.apache.shiro.web.util.SavedRequest;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class ClientSavedRequest extends SavedRequest {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8246299102806534603L;
    private String scheme;
    private String domain;
    private int port;
    private String contextPath;
    private String backUrl;

    public ClientSavedRequest(HttpServletRequest request, String backUrl) {
        super(request);
        this.scheme = request.getScheme();
        this.domain = request.getServerName();
        this.port = request.getServerPort();
        this.backUrl = backUrl;
        this.contextPath = request.getContextPath();
    }

    public String getScheme() {
        return scheme;
    }

    public String getDomain() {
        return domain;
    }

    public int getPort() {
        return port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getBackUrl() {
        return backUrl;
    }

    private static final String START_HTTP = "http://";
    private static final String START_HTTPS = "https://";
    private static final int PORT_80 = 80;
    private static final int PORT_443 = 443;

    private static final String MAGIC_HTTP = "http";
    private static final String MAGIC_HTTPS = "https";
    @Override
    public String getRequestUrl() {
        String requestURI = getRequestURI();
        //1
        if(backUrl != null) {
            if(backUrl.toLowerCase().startsWith(START_HTTP) || backUrl.toLowerCase().startsWith(START_HTTPS)) {
                return backUrl;
              //2
            } else if(!backUrl.startsWith(contextPath)) {
                requestURI = contextPath + backUrl;
            } else {
              //3
                requestURI = backUrl;
            }
        }
        //4
        StringBuilder requestUrl = new StringBuilder(scheme);
        requestUrl.append("://");
        //5
        requestUrl.append(domain);
        //6
        if(MAGIC_HTTP.equalsIgnoreCase(scheme) && port != PORT_80) {
            requestUrl.append(":").append(String.valueOf(port));
        } else if(MAGIC_HTTPS.equalsIgnoreCase(scheme) && port != PORT_443) {
            requestUrl.append(":").append(String.valueOf(port));
        }
        //7
        requestUrl.append(requestURI);
        //8
        if (backUrl == null && getQueryString() != null) {
            requestUrl.append("?").append(getQueryString());
        }
        return requestUrl.toString();
    }
}
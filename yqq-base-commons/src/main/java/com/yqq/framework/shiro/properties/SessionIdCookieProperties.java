package com.yqq.framework.shiro.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 
 * shiro session 配置
 * 
 * @author  libiao 工号
 * @see  [相关类/方法]
 */
@ConfigurationProperties(prefix = "shiro.session")
public class SessionIdCookieProperties {

    private String name = "sid";

    private String domain = "";

    private String path = "/";

    private boolean httpOnly = true;

    private int MaxAge = -1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public int getMaxAge() {
        return MaxAge;
    }

    public void setMaxAge(int maxAge) {
        MaxAge = maxAge;
    }
}

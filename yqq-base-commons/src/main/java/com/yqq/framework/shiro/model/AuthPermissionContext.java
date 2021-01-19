package com.yqq.framework.shiro.model;

import java.io.Serializable;
import java.util.Set;

/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class AuthPermissionContext implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2961090504204026334L;
    
    private Set<String> roles;
    private Set<String> permissions;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }


    @Override
    public String toString() {
        return "AuthPermissionContext{" +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
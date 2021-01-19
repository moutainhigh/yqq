package com.yqq.framework.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class SubjectSessionUtil {

    public static final String USER_SESSION_ATTRIBUTE_KEY = "USER_SESSION_ATTRIBUTE_KEY";

    public static final String RESOURCE_SESSION_ATTRIBUTE_KEY = "RESOURCE_SESSION_ATTRIBUTE_KEY";

    public static final String APP_SESSION_ATTRIBUTE_KEY = "APP_SESSION_ATTRIBUTE_KEY";

    public static final String PLATFORM_SESSION_ATTRIBUTE_KEY = "PLATFORM_SESSION_ATTRIBUTE_KEY";

    public static final String CURRENT_PLATFORM_ID_SESSION_ATTRIBUTE_KEY = "CURRENT_PLATFORM_ID_SESSION_ATTRIBUTE_KEY";

    public static final String PAD_SESSION_ATTRIBUTE_KEY = "PAD_SESSION_ATTRIBUTE_KEY";
    
    /**
     * 当前session 添加属性
     *
     * @param key
     * @param value
     */
    public static void setAttribute(Object key, Object value) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute(key, value);

    }


    /**
     * 当前session 获取属性
     *
     * @param key
     * @return
     */
    public static Object getAttribute(Object key) {
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession().getAttribute(key);

    }


    /**
     * 当前session 删除属性
     *
     * @param key
     * @return
     */
    public static Object removeAttribute(Object key) {

        Subject subject = SecurityUtils.getSubject();
        return subject.getSession().removeAttribute(key);
    }

}

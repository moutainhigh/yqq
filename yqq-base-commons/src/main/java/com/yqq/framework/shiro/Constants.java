package com.yqq.framework.shiro;


/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class Constants {

    public static final String ROLE_PRE_KEY = "role::";
    
    public static final long  DEFAULT_EXPIRE = 1000;
	
    public static final String CAPTCHA_KEY = "SE_KEY_MM_CODE";

    public static final String  SERVER_CURRENT_APP_URL = "webUrl";

    public static final String SERVER_PLATFORMS_KEY = "platforms";
    
    public static final String RESOURCE_PRE_KEY = "RESOURCE:";
    
    public static final String PERM_PRE_KEY = "perm:";

    /**
     * 应用 url集合
     */
    public static final String APP_RESOURCE_KEY = "app_resources:";
    /**
     * 应用 url 角色集合
     */
    public static final String APP_RESOURCE_ROLE_KEY = "app_resources_role:";
    

    /**
     * 应用 默认超时时间
     */
    public static final long APP_RESOURCE_DEFAULT_EXPIRE = 86400;
}

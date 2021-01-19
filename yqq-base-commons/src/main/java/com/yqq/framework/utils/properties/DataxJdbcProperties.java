/*
 * 文 件 名:  FileProperties.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2018年3月22日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * datax 入库相关参数 jdbc 配置  目前在系统中主要用于目标库上传
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Deprecated
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataxJdbcProperties {
    /**
     * jdbc url 地址
     */
    private String url;
    /**
     * jdbc 用户名
     */
    private String username;
    /**
     * jdbc 密码
     */
    private String password;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

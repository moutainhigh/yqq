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
 * datax 入库相关参数  目前在系统中主要用于目标库上传
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Deprecated
@Component
@ConfigurationProperties(prefix = "datax")
public class DataxProperties {
    /**
     * 远程 ssh IP地址
     */
    private String sshIp;
    

    /**
     * 远程 ssh IP端口号
     */
    private String sshPort;
    
    /**
     * 远程 ssh 用户名
     */
    private String sshUser;
    /**
     * 远程 ssh 密码
     */
    private String sshPwd;
    /**
     * datax 安装目录
     */
    private String installDir;
    /**
     * datax业务 json配置文件
     */
    private String jsonDir;
    /**
     * data 文件目录
     */
    private String dataDir;
    
    /**
     * uploaddata 文件上传目录 ftp的上传目录
     */
    private String uploadDataDir;
    
    public String getSshIp() {
        return sshIp;
    }

    public void setSshIp(String sshIp) {
        this.sshIp = sshIp;
    }

    public String getSshPort() {
        return sshPort;
    }

    public void setSshPort(String sshPort) {
        this.sshPort = sshPort;
    }

    public String getSshUser() {
        return sshUser;
    }

    public void setSshUser(String sshUser) {
        this.sshUser = sshUser;
    }

    public String getSshPwd() {
        return sshPwd;
    }

    public void setSshPwd(String sshPwd) {
        this.sshPwd = sshPwd;
    }

    public String getJsonDir() {
        return jsonDir;
    }

    public void setJsonDir(String jsonDir) {
        this.jsonDir = jsonDir;
    }

    public String getDataDir() {
        return dataDir;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    public String getUploadDataDir() {
        return uploadDataDir;
    }

    public void setUploadDataDir(String uploadDataDir) {
        this.uploadDataDir = uploadDataDir;
    }

    public String getInstallDir() {
        return installDir;
    }

    public void setInstallDir(String installDir) {
        this.installDir = installDir;
    }
}

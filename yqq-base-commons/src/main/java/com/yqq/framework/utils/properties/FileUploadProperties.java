/*
 * 文 件 名:  FileUploadProperties.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年11月25日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传相关参数
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Component
@ConfigurationProperties(prefix = "xw.file-upload")
public class FileUploadProperties {
    

    /**
     * 上传模式
     */
    private String mode;
    /**
     * 文件查看地址 主要用于图片上传及时预览
     */
    private String fileUrl;
    /**
     * 上传目录
     */
    private String uploadDir;
    
    private String host;
    
    private String port;
    
    private String userName;
    
    private String password;

    private String sftpMode;
    
    private String privateKey;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSftpMode() {
        return sftpMode;
    }

    public void setSftpMode(String sftpMode) {
        this.sftpMode = sftpMode;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}

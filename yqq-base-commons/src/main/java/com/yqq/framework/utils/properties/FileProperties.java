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
 * 文件上传 properties 配置信息
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    
    /**
     * 是否使用 ftp上传  fasle 不使用  true 使用
     */
    private String useFtpUpload;
    /**
     * 图片路径
     */
    private String url;
    /**
     * 图片ftp ip地址
     */
    private String ftpIp;
    /**
     * 图片ftp ip 端口
     */
    private String ftpPort;
    /**
     * 图片 ftp 用户名
     */
    private String ftpUser;
    /**
     * 图片 ftp 密码
     */
    private String ftpPwd;

    /**
     * 文件上传本地目录
     */
    private String uploadLocalDir;

    /**
     * 文件上传ftp目录
     */
    private String uploadFtpDir;

    public String getUseFtpUpload() {
        return useFtpUpload;
    }

    public void setUseFtpUpload(String useFtpUpload) {
        this.useFtpUpload = useFtpUpload;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFtpIp() {
        return ftpIp;
    }

    public void setFtpIp(String ftpIp) {
        this.ftpIp = ftpIp;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPwd() {
        return ftpPwd;
    }

    public void setFtpPwd(String ftpPwd) {
        this.ftpPwd = ftpPwd;
    }

    public String getUploadLocalDir() {
        return uploadLocalDir;
    }

    public void setUploadLocalDir(String uploadLocalDir) {
        this.uploadLocalDir = uploadLocalDir;
    }

    public String getUploadFtpDir() {
        return uploadFtpDir;
    }

    public void setUploadFtpDir(String uploadFtpDir) {
        this.uploadFtpDir = uploadFtpDir;
    }

}

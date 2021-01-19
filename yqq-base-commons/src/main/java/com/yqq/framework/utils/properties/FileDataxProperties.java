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
@ConfigurationProperties(prefix = "xw.datax")
public class FileDataxProperties {

    private String fileMode; 
    private String fileIp; 
    private String filePort;
    private String fileUsername;
    private String filePassword;
    private String sshIp;
    private String sshPort;
    private String sshUsername;
    private String sshPassword;
    private String redisCli;
    private String redisMode;
    private String redisHost;
    private String redisPort;
    private String redisPwd;
    private String redisDb;
    private String targetDir;
    private String softDir;
    private String sftpMode;
    private String privateKey;
    
    public String getFileMode() {
        return fileMode;
    }
    public void setFileMode(String fileMode) {
        this.fileMode = fileMode;
    }
    public String getFileIp() {
        return fileIp;
    }
    public void setFileIp(String fileIp) {
        this.fileIp = fileIp;
    }
    public String getFilePort() {
        return filePort;
    }
    public void setFilePort(String filePort) {
        this.filePort = filePort;
    }
    public String getFileUsername() {
        return fileUsername;
    }
    public void setFileUsername(String fileUsername) {
        this.fileUsername = fileUsername;
    }
    public String getFilePassword() {
        return filePassword;
    }
    public void setFilePassword(String filePassword) {
        this.filePassword = filePassword;
    }
    public String getSshIp() {
        return sshIp;
    }
    public void setSshIp(String sshIp) {
        this.sshIp = sshIp;
    }
    public String getSshUsername() {
        return sshUsername;
    }
    public void setSshUsername(String sshUsername) {
        this.sshUsername = sshUsername;
    }
    public String getSshPassword() {
        return sshPassword;
    }
    public void setSshPassword(String sshPassword) {
        this.sshPassword = sshPassword;
    }
    public String getRedisCli() {
        return redisCli;
    }
    public void setRedisCli(String redisCli) {
        this.redisCli = redisCli;
    }
    public String getRedisMode() {
        return redisMode;
    }
    public void setRedisMode(String redisMode) {
        this.redisMode = redisMode;
    }
    public String getRedisHost() {
        return redisHost;
    }
    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }
    public String getRedisPort() {
        return redisPort;
    }
    public void setRedisPort(String redisPort) {
        this.redisPort = redisPort;
    }
    public String getRedisPwd() {
        return redisPwd;
    }
    public void setRedisPwd(String redisPwd) {
        this.redisPwd = redisPwd;
    }
    public String getRedisDb() {
        return redisDb;
    }
    public void setRedisDb(String redisDb) {
        this.redisDb = redisDb;
    }
    public String getTargetDir() {
        return targetDir;
    }
    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }
    public String getSoftDir() {
        return softDir;
    }
    public void setSoftDir(String softDir) {
        this.softDir = softDir;
    }
    public String getSshPort() {
        return sshPort;
    }
    public void setSshPort(String sshPort) {
        this.sshPort = sshPort;
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

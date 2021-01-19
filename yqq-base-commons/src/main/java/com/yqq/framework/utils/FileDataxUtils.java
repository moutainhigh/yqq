/*
 * 文 件 名:  FileDataxUtils.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年11月25日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.SftpException;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class FileDataxUtils {
    
    private static Properties properties;

    private final static Logger logger = LoggerFactory.getLogger(com.yqq.framework.utils.FileUploadUtils.class);
    /**
     * 设置 properties    
     * @param properties 对properties进行赋值
     */
    public void setProperties(Properties properties) {
        FileDataxUtils.properties = properties;
    }

    /**
     * 上传文件
     * @param remoteDir 上传目录
     * @param is 文件流
     * @param newFileName 上传文件名
     */
    public static void uploadFile(String remoteDir, InputStream is, String newFileName) {
        String mode = properties.getProperty("mode");
        String host =  properties.getProperty("host");
        Integer port = Integer.valueOf(properties.getProperty("port"));
        String userName =  properties.getProperty("userName");
        String password =  properties.getProperty("password");
        String uploadDir =  properties.getProperty("uploadDir");
        String sftpMode =  properties.getProperty("sftpMode");
        String privateKey =  properties.getProperty("privateKey");
        
        switch (mode) {
            case "ftp":
                com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
                ftpUtil.setConfig(host, port, userName, password);
                ftpUtil.copyInputStreamToFile(is, newFileName, uploadDir + remoteDir);
                //ftpUtil.closeConnect();
                break;
            case "sftp":
                com.yqq.framework.utils.SFTPUtils sftpUtils = null;
                logger.info("uploadDir--------------{} ",uploadDir);
                //免密
                if("free".equals(sftpMode)) {
                    sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, host, port, privateKey);
                } else {
                    sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, password, host, port);
                }
                sftpUtils.login();
                try {
                    sftpUtils.upload(uploadDir, remoteDir, newFileName, is);
                    sftpUtils.logout();
                } catch (SftpException e) {
                    e.printStackTrace();
                    logger.error("sftp upload error ", e);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 
     * 文件下载本地
     * @param response
     * @param downloadFileName
     * @param realFileName
     */
    public static void downloadFile(HttpServletResponse response, String downloadFileName,String realFileName) {

        String mode = properties.getProperty("mode");
        String host =  properties.getProperty("host");
        Integer port = Integer.valueOf(properties.getProperty("port"));
        String userName =  properties.getProperty("userName");
        String password =  properties.getProperty("password");
        String sftpMode =  properties.getProperty("sftpMode");
        String privateKey =  properties.getProperty("privateKey");
        
        switch (mode) {
            case "ftp":
                com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
                ftpUtil.setConfig(host, port, userName, password);
                ftpUtil.downloadFile(response, downloadFileName, realFileName);
                break;
            case "sftp":
                com.yqq.framework.utils.SFTPUtils sftpUtils = null;
                //免密
                if("free".equals(sftpMode)) {
                    sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, host, port,privateKey);
                } else {
                    sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, password, host, port);
                }
                sftpUtils.login();
                sftpUtils.downloadFile(response, downloadFileName, realFileName);
                sftpUtils.logout();
                break;
            default:
                break;
        }
    }
    
    
    /**
     * 
     * 文件下载写到本地目录
     * @param filePath 带文件名文件
     */
    public static InputStream downloadFile(String downloadFileName) {

        String mode = properties.getProperty("mode");
        String host =  properties.getProperty("host");
        Integer port = Integer.valueOf(properties.getProperty("port"));
        String userName =  properties.getProperty("userName");
        String password =  properties.getProperty("password");
        String uploadDir =  properties.getProperty("uploadDir");
        String sftpMode =  properties.getProperty("sftpMode");
        String privateKey =  properties.getProperty("privateKey");
        
        String path = downloadFileName.substring(0, downloadFileName.lastIndexOf("/") + 1);
        String name = downloadFileName.substring(downloadFileName.lastIndexOf("/") + 1);
        
        InputStream inputStream = null;
        switch (mode) {
            case "ftp":
                com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
                ftpUtil.setConfig(host, port, userName, password);
                ftpUtil.setRemoteDir(uploadDir + path);
                inputStream =  ftpUtil.getInputStream(name);
                break;
            case "sftp":
                try {
                    com.yqq.framework.utils.SFTPUtils sftpUtils = null;
                    //免密
                    if("free".equals(sftpMode)) {
                        sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, host, port,privateKey);
                    } else {
                        sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, password, host, port);
                    }
                    sftpUtils.login();
                    inputStream = sftpUtils.downloadInputStream(uploadDir + path, name);
                } catch (SftpException e) {
                    logger.error("sftp upload error ", e);
                }
                break;
            default:
                break;
        }
        return inputStream;
    }

    /**
     * 生成文件名不包含后缀
     * @return fileName 文件名
     */
    public static String createFileName() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        return sd.format(new Date()) + getFixLenthString(9);
    }
    
    /**
     * 返回长度为【strLength】的随机数，在前面补0
     * @param strLength 长度
     * @return
     */
    private static String getFixLenthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        int pross = (int) ((1 + rm.nextDouble()) * Math.pow(10, strLength));
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }
}

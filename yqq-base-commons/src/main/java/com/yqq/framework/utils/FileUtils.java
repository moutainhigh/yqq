/*
 * 文 件 名:  FileUtils.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2017年10月12日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * 文件操作类
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
@Deprecated
public class FileUtils {
    private static Properties properties;

    /**
     * 设置 properties
     * @param properties 对properties进行赋值
     */
    public void setProperties(Properties properties) {
        FileUtils.properties = properties;
    }

    /**
     * 上传文件
     * @param remoteDir 上传目录
     * @param is 文件流
     * @param newFileName 上传文件名
     */
    public static void uploadFile(String remoteDir, InputStream is, String newFileName) {
        if ("true".equals(properties.getProperty("useFtpUpload"))) {
            com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
            ftpUtil.setConfig(properties.getProperty("fileFtpIp"), Integer.valueOf(properties.getProperty("fileFtpPort")),
                    properties.getProperty("fileFtpUser"), properties.getProperty("fileFtpPwd"));
            ftpUtil.copyInputStreamToFile(is, newFileName, properties.getProperty("uploadFtpDir") + remoteDir);
            ftpUtil.closeConnect();
        }
        else {
            try {
                uploadLocalFile((properties.getProperty("uploadLocalDir") + remoteDir).replace("/", File.separator), is, newFileName);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 本地上传
     * @param dir 上传目录
     * @param is 文件流
     * @param fileName 上传文件名
     * @throws IOException 
     */
    public static void uploadLocalFile(String dir, InputStream is, String fileName) throws IOException {
        FileOutputStream outputStream = null;
        try {
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File file = new File(dir + fileName);
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            byte temp[] = new byte[1024];
            int size = -1;
            while ((size = is.read(temp)) != -1) {
                outputStream.write(temp, 0, size);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            outputStream.close();
            is.close();
        }
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

    /**
     * 
     * 用于手机号目标库数据入库<p>
     * 该方法调用必须在文件上传后，可以放在文件上传成功后用户页面提交后再进行 数据入库
     * @param remoteFile 文件路径
     * @param batchNum   数据库 表批次号，导入成功后可以根据该批次号获取导入数据
     */
    @Deprecated
    public static void insertPhoneTemp(String remoteFile, String batchNum) {
        Map<String, String> params = new HashMap<>(5);
        params.put("remoteFile",  properties.getProperty("dataDir") + remoteFile);
        params.put("batchNum", batchNum);
        params.put("dbUser", properties.getProperty("dbUser"));
        params.put("dbPwd", properties.getProperty("dbPwd"));
        params.put("jdbcUrl", properties.getProperty("jdbcUrl"));
        Shell shell = new Shell(properties.getProperty("sshIp"), Integer.valueOf(properties.getProperty("sshPort")),
                properties.getProperty("sshUser"), properties.getProperty("sshPwd"));
        StringBuffer commandSb = new StringBuffer();
        commandSb.append("python ").append(properties.getProperty("installDir")).append("bin/datax.py ");
        commandSb.append("-p \"").append(getDataxParamStr(params)).append("\" ");
        commandSb.append(properties.getProperty("jsonDir"));
        commandSb.append("phoneTemp.json");
        shell.execute(commandSb.toString());
    }

    /**
     * 生成datax动态参数字符串
     * @param params 动态参数
     * @return
     */
    private static String getDataxParamStr(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append("-D").append(entry.getKey()).append("=").append(entry.getValue()).append(" ");
        }
        return sb.toString();
    }
}

/*
 * 文 件 名:  ZipUtils.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年8月16日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

/**
 * zip 工具类
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class ZipUtils {
    
    public static boolean unZipFiles(String zipPath, String descDir) {
        File zipFile = new File(zipPath);
        boolean flag = false;
        File pathFile = new File(descDir);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFile, Charset.forName("gbk"));//防止中文目录，乱码
            for(Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                //指定解压后的文件夹+当前zip文件的名称
                String outPath = (descDir+zipEntryName).replace("/", File.separator);
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
                if(!file.exists()){
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if(new File(outPath).isDirectory()){
                    continue;
                }
                //保存文件路径信息（可利用md5.zip名称的唯一性，来判断是否已经解压）
                System.err.println("当前zip解压之后的路径为：" + outPath);
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while((len=in.read(buf1))>0){
                    out.write(buf1,0,len);
                }
                in.close();
                out.close();
            }
            flag = true;
            //必须关闭，要不然这个zip文件一直被占用着，要删删不掉，改名也不可以，移动也不行，整多了，系统还崩了。
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
    

    public static boolean unZipFiles(File zipFile, String descDir) {
        boolean flag = false;
        File pathFile = new File(descDir);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFile, Charset.forName("gbk"));//防止中文目录，乱码
            for(Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                //指定解压后的文件夹+当前zip文件的名称
                String outPath = (descDir+zipEntryName).replace("/", File.separator);
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
                if(!file.exists()){
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if(new File(outPath).isDirectory()){
                    continue;
                }
                //保存文件路径信息（可利用md5.zip名称的唯一性，来判断是否已经解压）
                System.err.println("当前zip解压之后的路径为：" + outPath);
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while((len=in.read(buf1))>0){
                    out.write(buf1,0,len);
                }
                in.close();
                out.close();
            }
            flag = true;
            //必须关闭，要不然这个zip文件一直被占用着，要删删不掉，改名也不可以，移动也不行，整多了，系统还崩了。
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    /**
     * 
     * 从zip文件中获取指定文件并返回文件字符串内容，适用于 文本类 文件获取 
     * @param zipPath zip文件路径path
     * @param fileName 文件名
     * @return 正常文本文件返回内容，其他返回 null
     */
    public static String getZipFileToString(String zipPath, String fileName) {
        File zipFile = new File(zipPath);
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFile, Charset.forName("gbk"));//防止中文目录，乱码
            for(Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement();
                String zipEntryName = entry.getName();
                if(zipEntryName.equals(fileName)) {
                    InputStream in = zip.getInputStream(entry);
                    String str = IOUtils.toString(in, "utf-8");
                    in.close();
                    return str;
                }
            }
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * 查找指定文件是否在 zip文件中
     * @param zipPath zip文件路径path
     * @param fileName 需查找文件名
     * @return true 存在  false 不存在
     */
    @SuppressWarnings("resource")
    public static boolean isFileInZip(String zipPath, String fileName) {
        File zipFile = new File(zipPath);
        try {
            ZipFile zip = new ZipFile(zipFile, Charset.forName("gbk"));//防止中文目录，乱码
            for(Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement();
                String zipEntryName = entry.getName();
                if(zipEntryName.equals(fileName)) {
                    return true;
                }
            }
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 
     * 从zip文件中获取指定文件并返回文件字符串内容，适用于 文本类 文件获取 
     * @param zipPath zip文件路径path
     * @param fileName 文件名
     * @return 正常文本文件返回内容，其他返回 null
     */
    public static String getZipFileToString(File zipFile, String fileName) {
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFile, Charset.forName("gbk"));//防止中文目录，乱码
            for(Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement();
                String zipEntryName = entry.getName();
                if(zipEntryName.equals(fileName)) {
                    InputStream in = zip.getInputStream(entry);
                    String str = IOUtils.toString(in, "utf-8");
                    in.close();
                    return str;
                }
            }
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * 查找指定文件是否在 zip文件中
     * @param zipPath zip文件路径path
     * @param fileName 需查找文件名
     * @return true 存在  false 不存在
     */
    @SuppressWarnings("resource")
    public static boolean isFileInZip(File zipFile, String fileName) {
        try {
            ZipFile zip = new ZipFile(zipFile, Charset.forName("gbk"));//防止中文目录，乱码
            for(Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement();
                String zipEntryName = entry.getName();
                if(zipEntryName.equals(fileName)) {
                    return true;
                }
            }
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static File[] searchFile(String folderPath, String fileName) {
        File folder = new File(folderPath);
        File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return false;
                }
                if (file.getName().toLowerCase().endsWith(fileName)) {
                    return true;
                }
                return false;
            }
        });
        return subFolders;
    }
    
}

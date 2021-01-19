/*
 * 文 件 名:  FileUploadUtils.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年11月25日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import com.jcraft.jsch.SftpException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * <功能描述>
 *
 * @author yangchuan
 * @see [相关类/方法]
 */
public class FileUploadUtils {

	private static Properties properties;

	private final static Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

	/**
	 * 设置 properties
	 *
	 * @param properties 对properties进行赋值
	 */
	public void setProperties(Properties properties) {
		FileUploadUtils.properties = properties;
	}

	/**
	 * 上传文件
	 *
	 * @param remoteDir   上传目录
	 * @param is          文件流
	 * @param newFileName 上传文件名
	 */
	public static void uploadFile(String remoteDir, InputStream is, String newFileName) {
		String mode = properties.getProperty("mode");
		String host = properties.getProperty("host");
		Integer port = Integer.valueOf(properties.getProperty("port"));
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		String uploadDir = properties.getProperty("uploadDir");
		String sftpMode = properties.getProperty("sftpMode");
		String privateKey = properties.getProperty("privateKey");

		switch (mode) {
			case "local":
				try {
					uploadLocalFile((uploadDir + remoteDir).replace("/", File.separator), is, newFileName);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("local upload error ", e);
				}
				break;
			case "ftp":
				com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
				ftpUtil.setConfig(host, port, userName, password);
				ftpUtil.copyInputStreamToFile(is, newFileName, uploadDir + remoteDir);
				ftpUtil.closeConnect();
				break;
			case "sftp":
				com.yqq.framework.utils.SFTPUtils sftpUtils = null;
				//免密
				if ("free".equals(sftpMode)) {
					sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, host, port, privateKey);
				} else {
					sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, password, host, port);
				}
				try {
					sftpUtils.login();
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
	 * 上传文件
	 *
	 * @param baseDir     上传基础目录
	 * @param remoteDir   上传目录
	 * @param is          文件流
	 * @param newFileName 上传文件名
	 */
	public static void uploadFile(String baseDir, String remoteDir, InputStream is, String newFileName) {
		String mode = properties.getProperty("mode");
		String host = properties.getProperty("host");
		Integer port = Integer.valueOf(properties.getProperty("port"));
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		String uploadDir = properties.getProperty("uploadDir");
		String sftpMode = properties.getProperty("sftpMode");
		String privateKey = properties.getProperty("privateKey");
		if (StringUtils.isNotEmpty(baseDir)) {
			uploadDir = baseDir;
		}

		switch (mode) {
			case "local":
				try {
					uploadLocalFile((uploadDir + remoteDir).replace("/", File.separator), is, newFileName);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("local upload error {}", e);
				}
				break;
			case "ftp":
				com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
				ftpUtil.setConfig(host, port, userName, password);
				ftpUtil.copyInputStreamToFile(is, newFileName, uploadDir + remoteDir);
				ftpUtil.closeConnect();
				break;
			case "sftp":
				com.yqq.framework.utils.SFTPUtils sftpUtils = null;
				//免密
				if ("free".equals(sftpMode)) {
					sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, host, port, privateKey);
				} else {
					sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, password, host, port);
				}
				try {
					sftpUtils.login();
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
	 * 文件下载本地
	 *
	 * @param response
	 * @param downloadFileName
	 * @param realFileName
	 */
	public static void downloadFile(HttpServletResponse response, String downloadFileName, String realFileName) {

		String mode = properties.getProperty("mode");
		String host = properties.getProperty("host");
		Integer port = Integer.valueOf(properties.getProperty("port"));
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		String sftpMode = properties.getProperty("sftpMode");
		String privateKey = properties.getProperty("privateKey");
		switch (mode) {
			case "local":
				downloadLocalFile(response, downloadFileName, realFileName);
				break;
			case "ftp":
				com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
				ftpUtil.setConfig(host, port, userName, password);
				ftpUtil.downloadFile(response, downloadFileName, realFileName);
				break;
			case "sftp":
				com.yqq.framework.utils.SFTPUtils sftpUtils = null;
				//免密
				if ("free".equals(sftpMode)) {
					sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, host, port, privateKey);
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
	 * http 下载
	 *
	 * @param response
	 * @param downloadFileName
	 * @param realFileName
	 */
	public static void downloadLocalFile(HttpServletResponse response, String downloadFileName, String realFileName) {
		BufferedOutputStream buff = null;
		ServletOutputStream outStr = null;
		try {
			InputStream is = new FileInputStream(downloadFileName);
			// 设置响应的内容类型
			response.setContentType("text/plain");
			response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realFileName, "UTF-8"));
			outStr = response.getOutputStream();
			buff = new BufferedOutputStream(outStr);
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) > 0) {
				buff.write(buffer, 0, len);
			}
			buff.flush();
			buff.close();
			is.close();
			logger.info("文件下载成功");
		} catch (IOException e) {
			logger.error("下载文件失败" + e.getMessage());
			throw new RuntimeException("下载文件失败" + e.getMessage());
		} finally {
			try {
				buff.close();
				outStr.close();
			} catch (Exception e) {
				logger.error("关闭流对象出错 e:{}", e);
			}
		}
	}

	/**
	 * 文件下载写到本地目录
	 *
	 * @param filePath 带文件名文件
	 * @throws IOException
	 */
	public static File downloadTextFile(String downloadFileName) {

		String mode = properties.getProperty("mode");
		String host = properties.getProperty("host");
		Integer port = Integer.valueOf(properties.getProperty("port"));
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		String uploadDir = properties.getProperty("uploadDir");
		String sftpMode = properties.getProperty("sftpMode");
		String privateKey = properties.getProperty("privateKey");

		String path = downloadFileName.substring(0, downloadFileName.lastIndexOf("/") + 1);
		String name = downloadFileName.substring(downloadFileName.lastIndexOf("/") + 1);

		InputStream inputStream = null;
		File file = null;
		try {
			switch (mode) {
				case "local":
					file = new File(uploadDir + downloadFileName);
					break;
				case "ftp":
					com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
					ftpUtil.setConfig(host, port, userName, password);
					ftpUtil.setRemoteDir(uploadDir + path);
					inputStream = ftpUtil.getInputStream(name);
					file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
					FileUtils.copyInputStreamToFile(inputStream, file);
					break;
				case "sftp":
					com.yqq.framework.utils.SFTPUtils sftpUtils = null;
					//免密
					if ("free".equals(sftpMode)) {
						sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, host, port, privateKey);
					} else {
						sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, password, host, port);
					}
					try {
						sftpUtils.login();
						inputStream = sftpUtils.downloadInputStream(uploadDir + path, name);
						file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
						FileUtils.copyInputStreamToFile(inputStream, file);
					} catch (SftpException e) {
						logger.error("sftp upload error ", e);
					} finally {
						sftpUtils.logout();
					}
					break;
				default:
					break;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("local downloadFile error {}", e1);
		}
		return file;
	}

	/**
	 * 本地上传
	 *
	 * @param dir      上传目录
	 * @param is       文件流
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
		} catch (IOException e) {
			e.printStackTrace();
            logger.error("本地上传 local upload error {}", e);
		} finally {
			outputStream.close();
			is.close();
		}
	}


	/**
	 * 生成文件名不包含后缀
	 *
	 * @return fileName 文件名
	 */
	public static String createFileName() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		return sd.format(new Date()) + getFixLenthString(9);
	}

	/**
	 * 返回长度为【strLength】的随机数，在前面补0
	 *
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
	 * 获取文件流
	 *
	 * @param fileUrl
	 * @return
	 */
	public static InputStream getFileInputStream(String fileUrl) {
		InputStream is = null;
		String mode = properties.getProperty("mode");
		String host = properties.getProperty("host");
		Integer port = Integer.valueOf(properties.getProperty("port"));
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		String uploadDir = properties.getProperty("uploadDir");
		String sftpMode = properties.getProperty("sftpMode");
		String privateKey = properties.getProperty("privateKey");


		String dir = fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1);
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

		switch (mode) {
			case "local":
				try {
					File file = new File(uploadDir + fileUrl);
					if (!file.exists() || file.isDirectory()) {
						return null;
					}
					is = new FileInputStream(file);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("获取文件流  error {}", e);
				}
				return is;
			case "ftp":
				com.yqq.framework.utils.FtpUtils ftpUtil = new com.yqq.framework.utils.FtpUtils();
				try {
					ftpUtil.setConfig(host, port, userName, password);
					ftpUtil.setRemoteDir(dir);
					is = ftpUtil.getInputStream(fileName);
				} catch (Exception e) {
                    logger.error("获取文件流  error {}", e);
					return null;
				} finally {
					ftpUtil.closeConnect();
				}
				return is;
			case "sftp":
				com.yqq.framework.utils.SFTPUtils sftpUtils = null;
				//免密
				if ("free".equals(sftpMode)) {
					sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, host, port, privateKey);
				} else {
					sftpUtils = new com.yqq.framework.utils.SFTPUtils(userName, password, host, port);
				}
				try {
					sftpUtils.login();
					is = sftpUtils.downloadInputStream(dir, fileName);
				} catch (SftpException e) {
					e.printStackTrace();
					logger.error("sftp upload error {}", e);
				} finally {
					sftpUtils.logout();
				}
				return is;
			default:
				break;
		}
		return null;
	}

	/**
	 * 获取图片尺寸
	 *
	 * @param is
	 * @return [宽，高]
	 */
	public static int[] getImgSize(InputStream is) {
		int[] sizeArr = new int[2];
		BufferedImage sourceImg = null;
		try {
			sourceImg = ImageIO.read(is);
			sizeArr[0] = sourceImg.getWidth();
			sizeArr[1] = sourceImg.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("图片不存在");
		}
		return sizeArr;
	}

}

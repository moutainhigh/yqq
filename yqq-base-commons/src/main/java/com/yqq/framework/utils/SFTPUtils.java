/*
 * 文 件 名:  SFTPUtils.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年11月25日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Vector;

/**
 * <功能描述>
 *
 * @author yangchuan
 * @see [相关类/方法]
 */
public class SFTPUtils {

	private transient Logger log = LoggerFactory.getLogger(this.getClass());

	private ChannelSftp sftp;

	private Session session;
	/**
	 * SFTP 登录用户名
	 */
	private String username;
	/**
	 * SFTP 登录密码
	 */
	private String password;
	/**
	 * 私钥
	 */
	private String privateKey;
	/**
	 * SFTP 服务器地址IP地址
	 */
	private String host;
	/**
	 * SFTP 端口
	 */
	private int port;


	/**
	 * 构造基于密码认证的sftp对象
	 */
	public SFTPUtils(String username, String password, String host, int port) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	/**
	 * 构造基于秘钥认证的sftp对象
	 */
	public SFTPUtils(String username, String host, int port, String privateKey) {
		this.username = username;
		this.host = host;
		this.port = port;
		this.privateKey = privateKey;
	}

	public SFTPUtils() {
	}


	/**
	 * 连接sftp服务器
	 */
	public void login() {
		try {
			JSch jsch = new JSch();
			if (privateKey != null) {
				jsch.addIdentity(privateKey);// 设置私钥
			}

			session = jsch.getSession(username, host, port);

			if (password != null) {
				session.setPassword(password);
			}
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();

			sftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接 server
	 */
	public void logout() {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}


	/**
	 * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
	 *
	 * @param basePath     服务器的基础路径
	 * @param directory    上传到该目录
	 * @param sftpFileName sftp端文件名
	 * @param input           输入流
	 */
	public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
		try {
			sftp.cd(basePath);
			sftp.cd(directory);
		} catch (SftpException e) {
			//目录不存在，则创建文件夹
			String[] dirs = directory.split("/");
			String tempPath = basePath;
			for (String dir : dirs) {
				if (null == dir || "".equals(dir)) continue;
				tempPath += "/" + dir;
				try {
					sftp.cd(tempPath);
				} catch (SftpException ex) {
					sftp.mkdir(tempPath);
					sftp.cd(tempPath);
				}
			}
		}
		sftp.put(input, sftpFileName);  //上传文件
	}


	/**
	 * 下载文件。
	 *
	 * @param directory    下载目录
	 * @param downloadFile 下载的文件
	 * @param saveFile     存在本地的路径
	 */
	public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		File file = new File(saveFile);
		sftp.get(downloadFile, new FileOutputStream(file));
	}

	/**
	 * http 下载
	 *
	 * @param response
	 * @param downloadFileName
	 * @param realFileName
	 */
	public void downloadFile(HttpServletResponse response, String downloadFileName, String realFileName) {
		InputStream is = null;
		BufferedOutputStream buff = null;
		ServletOutputStream outStr = null;
		try {
			String directory = downloadFileName.substring(0, downloadFileName.lastIndexOf("/"));
			String name = downloadFileName.substring(downloadFileName.lastIndexOf("/") + 1);
			System.out.println(directory);
			System.out.println(name);
			if (StringUtils.isNotEmpty(directory)) {
				sftp.cd(directory);
			}
			System.out.println(name);
			is = sftp.get(name);

			if (is == null) {
				throw new RuntimeException("下载失败，检查文件是否存在");
			}
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
			log.info("文件下载成功");
		} catch (IOException | SftpException e) {
			log.error("下载文件失败" + e.getMessage());
			throw new RuntimeException("下载文件失败" + e.getMessage());
		} finally {
			try {
				buff.close();
				outStr.close();
			} catch (Exception e) {
				log.error("关闭流对象出错 e:{}", e);
			}
		}
	}

	/**
	 * 下载文件
	 *
	 * @param directory    下载目录
	 * @param downloadFile 下载的文件名
	 * @return 字节数组
	 */
	public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		InputStream is = sftp.get(downloadFile);

		byte[] fileData = IOUtils.toByteArray(is);

		return fileData;
	}

	/**
	 * 下载文件
	 *
	 * @param directory    下载目录
	 * @param downloadFile 下载的文件名
	 * @return 字节数组
	 */
	public InputStream downloadInputStream(String directory, String downloadFile) throws SftpException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		InputStream is = sftp.get(downloadFile);
		return is;
	}

	/**
	 * 下载文件
	 *
	 * @param downloadFile 下载的全路径文件名
	 * @return 字节数组
	 */
	public InputStream downloadInputStream(String downloadFile) throws SftpException {
		String directory = downloadFile.substring(0, downloadFile.lastIndexOf("/") + 1);
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		InputStream is = sftp.get(downloadFile);
		return is;
	}


	/**
	 * 删除文件
	 *
	 * @param directory  要删除文件所在目录
	 * @param deleteFile 要删除的文件
	 */
	public void delete(String directory, String deleteFile) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}


	/**
	 * 列出目录下的文件
	 *
	 * @param directory 要列出的目录
	 * @param sftp
	 */
	public Vector<?> listFiles(String directory) throws SftpException {
		return sftp.ls(directory);
	}

	//上传文件测试
	public static void main(String[] args) throws SftpException, IOException {
		SFTPUtils sftp = new SFTPUtils("devtest", "Dev10101", "192.168.22.225", 22);
		sftp.login();
		File file = new File("D:\\新建文本文档.txt");
		InputStream is = new FileInputStream(file);

		sftp.upload("/home/devtest/deploy_yxgj", "uptest", "test_sftp.txt", is);
		sftp.logout();
	}
}

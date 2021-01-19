/*
 * 文 件 名:  Shell.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2017年4月11日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.yqq.framework.model.ShellUserInfo;
/**
 * <功能描述>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class Shell {

	/**
	 * 远程主机的ip地址
	 */
    private String ip;

    /**
     * 远程主机的ip 端口
     */
    private int port;
    
    /**
     * 远程主机登录用户名
     */
    private String username;
    /**
     * 远程主机的登录密码
     */
    private String password;
    
    private String privateKey;
    /**
     * 设置ssh连接的远程端口
     */
    public static final int DEFAULT_SSH_PORT = 22;  
    /**
     * 保存输出内容的容器
     */
    private ArrayList<String> stdout;

    /**
     * 初始化登录信息
     * @param ip
     * @param username
     * @param password
     */
    public Shell(final String ip, final String username, final String password) {
         this.ip = ip;
         this.port = DEFAULT_SSH_PORT;
         this.username = username;
         this.password = password;
         stdout = new ArrayList<String>();
    }
    
    /**
     * 初始化登录信息
     * @param ip
     * @param username
     * @param password
     */
    public Shell(final String ip, final int port, final String username, final String password) {
         this.ip = ip;
         this.port = port;
         this.username = username;
         this.password = password;
         stdout = new ArrayList<String>();
    }
    
    /**
     * 初始化登录信息
     * @param username
     * @param privateKey
     * @param ip
     * @param port
     */
    public Shell(final String username, final String privateKey,final String ip, final int port) {
         this.ip = ip;
         this.port = port;
         this.username = username;
         this.privateKey = privateKey;
         stdout = new ArrayList<String>();
    }
    
    /**
     * 执行shell命令
     * @param command
     * @return
     */
    public int execute(final String command) {
        int returnCode = 0;
        JSch jsch = new JSch();
        ShellUserInfo userInfo = new ShellUserInfo();

        try {
            //创建session并且打开连接，因为创建session之后要主动打开连接
            Session session = jsch.getSession(username, ip, port);
            if(privateKey != null) {
                jsch.addIdentity(privateKey);
            }
            if(password!=null && password!="") {
                session.setPassword(password);
            }
            session.setConfig("StrictHostKeyChecking", "no");
            if(password!=null && password!="") {
                session.setUserInfo(userInfo);
            }
            session.connect();

            //打开通道，设置通道类型，和执行的命令
            Channel channel = session.openChannel("exec");
            ChannelExec channelExec = (ChannelExec)channel;
            channelExec.setCommand(command);

            channelExec.setInputStream(null);
            BufferedReader input = new BufferedReader(new InputStreamReader
                    (channelExec.getInputStream()));

            channelExec.connect();
            System.out.println("The remote command is :" + command);

            //接收远程服务器执行命令的结果
            String line;
            while ((line = input.readLine()) != null) {  
                stdout.add(line);  
            }  
            input.close();  

            // 得到returnCode
            if (channelExec.isClosed()) {  
                returnCode = channelExec.getExitStatus();  
            }  

            // 关闭通道
            channelExec.disconnect();
            //关闭session
            session.disconnect();

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnCode;
    }
    /**
     * get stdout
     * @return
     */
    public ArrayList<String> getStandardOutput() {
        return stdout;
    }

    public static void main(final String [] args) {  
        Shell shell = new Shell("192.168.23.126", "devtest", "Dev10101");
        shell.execute("ls");
//        shell.execute("python /home/datax/bin/datax.py /home/targetuser/json/targetuser.json");
//        shell.execute("python /home/datax/bin/datax.py -p\"-Dsyndate=20170405 -Dlastphonenum=6\" /home/targetuser/json/mysqltomysql.json");

        ArrayList<String> stdout = shell.getStandardOutput();
        for (String str : stdout) {  
            System.out.println(str);  
        }  
    }  
}

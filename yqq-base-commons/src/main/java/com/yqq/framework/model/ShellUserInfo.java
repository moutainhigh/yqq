/*
 * 文 件 名:  MyUserInfo.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhuyao 1824
 * 修改时间:  2017年4月11日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.model;

import com.jcraft.jsch.UserInfo;
/**
 * <功能描述>
 * 
 * @author  zhuyao 1824
 * @see  [相关类/方法]
 */
public class ShellUserInfo implements UserInfo {

	 String passwd;

	 @Override
     public String getPassword() {
         return passwd;
     }
	 @Override
     public boolean promptYesNo(String str) {
         return true;
     }
     @Override
     public String getPassphrase() {
         return null;
     }
     @Override
     public boolean promptPassphrase(String message) {
         return true;
     }
     @Override
     public boolean promptPassword(String message) {
         return true;
     }
     @Override
     public void showMessage(String message) {
         
     }
}

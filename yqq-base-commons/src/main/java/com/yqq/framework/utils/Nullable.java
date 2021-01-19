/*
 * 文 件 名:  Nullable.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2018年7月30日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
public @interface Nullable {
}

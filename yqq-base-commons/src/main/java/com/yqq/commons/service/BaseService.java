package com.yqq.commons.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseService {
    /**
     *通用日志记录
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 扩展特殊日志记录
     */
    protected Logger ilogger = LoggerFactory.getLogger("extLog");
}

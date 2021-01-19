package com.yqq.config.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * 
 * 配置中心启动类
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {
    private final static Logger logger = LoggerFactory.getLogger(com.yqq.config.bootstrap.ConfigApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(com.yqq.config.bootstrap.ConfigApplication.class, args);
        logger.info("start config server application success");
    }
}

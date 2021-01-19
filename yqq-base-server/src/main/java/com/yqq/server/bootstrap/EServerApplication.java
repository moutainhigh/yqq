package com.yqq.server.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@SpringBootApplication
@EnableEurekaServer
public class EServerApplication {
    private final static Logger logger = LoggerFactory.getLogger(EServerApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(EServerApplication.class, args);
        logger.info("start Eureka server application success");
    }
}

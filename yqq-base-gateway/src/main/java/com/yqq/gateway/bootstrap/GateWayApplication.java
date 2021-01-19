package com.yqq.gateway.bootstrap;

import com.yqq.gateway.bootstrap.config.ResourceServerConfig;
import com.yqq.gateway.bootstrap.config.WebMvcConfiguration;
import com.yqq.gateway.bootstrap.config.swagger.GatewaySwaggerResourcesProvider;
import com.yqq.gateway.filter.TokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Import({ResourceServerConfig.class,GatewaySwaggerResourcesProvider.class, WebMvcConfiguration.class})
@EnableZuulProxy
@SpringBootApplication
@EnableSwagger2
@EnableHystrix
@EnableEurekaClient
public class GateWayApplication {
    private final static Logger logger = LoggerFactory.getLogger(GateWayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
        logger.info("start gateway application success");
    }
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}

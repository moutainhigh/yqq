package com.yqq.auth.bootstrap;

import com.yqq.auth.bootstrap.config.*;
import com.yqq.auth.utils.KryoRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;


/**
 * 
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Import({SecurityConfiguration.class, AuthorizationServerConfiguration.class,Swagger2Configuration.class, MybatisConfig.class, UserOAuth2WebResponseExceptionTranslator.class})
@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.yqq.auth.controller","com.yqq.auth.service"})
public class AuthApplication {
    private final static Logger logger = LoggerFactory.getLogger(AuthApplication.class);

    public static void main(String[] args) {
        
        SpringApplication.run(AuthApplication.class, args);
        logger.info("start auth application success");
    }
    
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new KryoRedisSerializer());
        template.setHashValueSerializer(new KryoRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}

package com.yqq.third.bootstrap;

import com.yqq.framework.utils.KryoRedisSerializer;
import com.yqq.third.bootstrap.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.UnknownHostException;


@SpringBootApplication
@Import({
    MybatisConfig.class, 
    com.yqq.third.bootstrap.config.Swagger2Config.class,
    RestTemplateConfig.class,
    WebMvcConfiguration.class
    })
@ComponentScan({"com.yqq.third.controller",
    "com.yqq.third.service",
    "com.yqq.commons.cached",
    "com.yqq.commons.service"
    })
@EnableAsync
public class MicroThirdApplication {
    
    private final static Logger logger = LoggerFactory.getLogger(MicroThirdApplication.class);

    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MicroThirdApplication.class, args);
        logger.info("start micro third application success");
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

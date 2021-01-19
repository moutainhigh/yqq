package com.yqq.third.bootstrap.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@MapperScan({"com.yqq.third.dao"})
public class MybatisConfig {

}

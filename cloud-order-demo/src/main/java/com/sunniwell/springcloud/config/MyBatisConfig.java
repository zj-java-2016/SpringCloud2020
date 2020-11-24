package com.sunniwell.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：zj
 * @date ：Created in 2020/11/5 14:53
 * @description：
 * @version: $
 */
@Configuration
@MapperScan({"com.sunniwell.springcloud.dao"})
public class MyBatisConfig {
}

package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：zj
 * @date ：Created in 2020/10/28 11:29
 * @description：负载均衡的算法切换
 * @version: $
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule getIRule(){
        return new RandomRule();
    }
}

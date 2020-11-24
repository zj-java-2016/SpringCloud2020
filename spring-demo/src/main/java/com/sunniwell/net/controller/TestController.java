package com.sunniwell.net.controller;

import cn.hutool.core.thread.ThreadUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

/**
 * @author ：zj
 * @date ：Created in 2020/11/6 18:00
 * @description：
 * @version: $
 */
@RestController
public class TestController {

    @Resource
    private RedissonClient redissonClient;

    @GetMapping
    public String getTest(){
        System.out.println("=================准备入锁");
        RLock lock = redissonClient.getLock("123");
        lock.lock();
        ThreadUtil.sleep(30000);
        System.out.println("=================进入锁");
        lock.unlock();
        System.out.println("=================出入锁");

        LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
        return "test";
    }
}

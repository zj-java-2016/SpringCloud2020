package com.atguigu.springcloud.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.block.FlowLimitBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：zj
 * @date ：Created in 2020/11/3 17:47
 * @description：
 * @version: $
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    @SentinelResource(value = "testA",
            blockHandlerClass = FlowLimitBlockHandler.class,
            blockHandler = "blockHandler1")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    @SentinelResource(value = "testB",
            blockHandlerClass = FlowLimitBlockHandler.class,
            blockHandler = "blockHandler2")
    public String testB() {
        ThreadUtil.sleep(500);
        return "------testB";
    }
}

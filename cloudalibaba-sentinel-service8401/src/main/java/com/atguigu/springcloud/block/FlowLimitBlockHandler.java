package com.atguigu.springcloud.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.deploy.security.BlockedException;

/**
 * @author ：zj
 * @date ：Created in 2020/11/3 17:58
 * @description：
 * @version: $
 */
public class FlowLimitBlockHandler {

    public static String blockHandler1(BlockException excption){
        return "服务不可用---testA";
    }

    public static String blockHandler2(BlockException excption){
        return "服务不可用---testB";
    }
}

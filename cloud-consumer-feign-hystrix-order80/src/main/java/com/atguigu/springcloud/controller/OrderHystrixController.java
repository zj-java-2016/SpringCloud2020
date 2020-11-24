package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：zj
 * @date ：Created in 2020/10/29 11:51
 * @description：
 * @version: $
 */
@Slf4j
//@DefaultProperties(defaultFallback = "fallbackeMethod")
@RestController
@RequestMapping("/order")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable Integer id){
        return paymentHystrixService.paymentInfo_ok(id);
    }

    /*@HystrixCommand(fallbackMethod = "paymentInfo_timeHandler",commandProperties = {
            //3秒钟以内就是正常的业务逻辑
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    })*/
    //@HystrixCommand
    @GetMapping("/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable Integer id){
        return paymentHystrixService.paymentInfo_timeout(id);
    }
    /*public String paymentInfo_timeHandler(@PathVariable Integer id){
        return "呜呜呜";
    }

    public String fallbackeMethod(){
        return "全局异常，请重试";
    }*/
}

package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：zj
 * @date ：Created in 2020/10/29 11:25
 * @description：
 * @version: $
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable Integer id){
        return paymentService.paymentInfo_ok(id);
    }

    @GetMapping("/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable Integer id){
        return paymentService.paymentInfo_time(id);
    }

    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }
}

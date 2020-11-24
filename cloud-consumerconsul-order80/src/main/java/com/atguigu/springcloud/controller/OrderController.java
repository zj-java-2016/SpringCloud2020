package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ：zj
 * @date ：Created in 2020/10/28 8:57
 * @description：
 * @version: $
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    public static final String PAYMENT_UTL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String create(Payment payment){
        return restTemplate.getForObject(PAYMENT_UTL + "/payment/consul", String.class);
    }
}

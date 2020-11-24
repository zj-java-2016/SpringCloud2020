package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.feign.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：zj
 * @date ：Created in 2020/10/29 10:29
 * @description：
 * @version: $
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/feign/{id}")
    public CommonResult create(@PathVariable("id") Long id){
        return paymentFeignService.create(id);
    }
}

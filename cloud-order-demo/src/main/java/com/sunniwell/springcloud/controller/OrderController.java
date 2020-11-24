package com.sunniwell.springcloud.controller;

import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.entity.OrderInfo;
import com.sunniwell.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 16:30
 * @description：
 * @version: $
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping
    public CommonResult<List<OrderInfo>> listOrder(){
        return orderService.listOrder();
    }

    @GetMapping("/create")
    public CommonResult create(OrderInfo order){
        return orderService.create(order);
    }
}

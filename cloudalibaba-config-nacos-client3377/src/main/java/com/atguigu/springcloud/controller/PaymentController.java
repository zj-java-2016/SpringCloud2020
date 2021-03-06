package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：zj
 * @date ：Created in 2020/10/27 15:26
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

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("===插入结果：{}", result);

        if(result > 0){
            return new CommonResult(200, "插入成功,端口号：" + serverPort, result);
        }else {
            return new CommonResult(444, "插入数据库失败");
        }
    }

    @GetMapping("/{id}")
    public CommonResult create(@PathVariable Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("===查询结果：{}", paymentById);

        if(paymentById != null){
            return new CommonResult(200, "查询成功,端口号：" + serverPort, paymentById);
        }else {
            return new CommonResult(444, "未查询到对应记录");
        }
    }
}

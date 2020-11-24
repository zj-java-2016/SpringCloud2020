package com.atguigu.springcloud.service;

/**
 * @author ：zj
 * @date ：Created in 2020/10/29 11:21
 * @description：
 * @version: $
 */
public interface PaymentService {

    String paymentInfo_ok(Integer id);

    String paymentInfo_time(Integer id);

    String paymentCircuitBreaker(Integer id);
}

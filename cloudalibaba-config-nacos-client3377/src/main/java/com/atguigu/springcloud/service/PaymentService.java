package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @author ：zj
 * @date ：Created in 2020/10/27 15:23
 * @description：
 * @version: $
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}

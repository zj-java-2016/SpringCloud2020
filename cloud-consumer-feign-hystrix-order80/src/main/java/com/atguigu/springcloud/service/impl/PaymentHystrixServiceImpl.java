package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author ：zj
 * @date ：Created in 2020/10/29 14:51
 * @description：
 * @version: $
 */
@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "请重试";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "稍后请重试";
    }
}

package com.sunniwell.springcloud.feign.impl;

import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.feign.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 18:11
 * @description：
 * @version: $
 */
@Component
public class AccountServiceImpl implements AccountService {

    @Override
    public CommonResult decrease(Long userId, BigDecimal money) {
        return new CommonResult(400, "服务器繁忙，请稍后重试");
    }
}

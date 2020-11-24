package com.sunniwell.springcloud.feign.impl;

import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.feign.StorageService;
import org.springframework.stereotype.Component;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 18:29
 * @description：
 * @version: $
 */
@Component
public class StorageServiceImpl implements StorageService {

    @Override
    public CommonResult decrease(Long productId, Integer count) {
        return new CommonResult(400, "服务器繁忙，请稍后重试");
    }
}

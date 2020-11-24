package com.sunniwell.springcloud.feign;

import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.feign.impl.StorageServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 18:28
 * @description：
 * @version: $
 */
@FeignClient(value = "cloud-storage-demo", fallback = StorageServiceImpl.class)
public interface StorageService {

    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId,
                                 @RequestParam("count") Integer count);
}

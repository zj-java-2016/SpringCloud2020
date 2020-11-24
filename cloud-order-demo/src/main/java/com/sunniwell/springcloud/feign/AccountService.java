package com.sunniwell.springcloud.feign;

import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.feign.impl.AccountServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 18:10
 * @description：
 * @version: $
 */
@FeignClient(value = "cloud-account-demo", fallback = AccountServiceImpl.class)
public interface AccountService {

    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId,
                                 @RequestParam("money") BigDecimal money);
}

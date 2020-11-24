package com.sunniwell.springcloud.controller;

import com.sunniwell.springcloud.entity.Account;
import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 14:39
 * @description：
 * @version: $
 */
@RefreshScope
@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${config.info}")
    private String configInfo;

    @Resource
    private AccountService accountService;

    @GetMapping
    public CommonResult<List<Account>> listAccount(){
        return accountService.listAccount();
    }

    @GetMapping("/config/info")
    public String getConfigInfo(){
        return configInfo;
    }

    @PostMapping("/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,
                                 @RequestParam("money") BigDecimal money){
        return accountService.decrease(userId, money);
    }
}

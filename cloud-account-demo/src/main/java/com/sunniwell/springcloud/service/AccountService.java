package com.sunniwell.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunniwell.springcloud.entity.Account;
import com.sunniwell.springcloud.entity.CommonResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 14:36
 * @description：
 * @version: $
 */
public interface AccountService extends IService<Account> {

    /**
     * 返回所有用户信息
     * @return
     */
    CommonResult<List<Account>> listAccount();

    /**
     * 减账户
     * @param userId
     * @param money
     * @return
     */
    CommonResult decrease(Long userId, BigDecimal money);
}

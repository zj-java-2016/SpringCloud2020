package com.sunniwell.springcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunniwell.springcloud.dao.AccountDao;
import com.sunniwell.springcloud.entity.Account;
import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.service.AccountService;
import org.springframework.stereotype.Service;

import javax.xml.ws.spi.http.HttpContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 14:37
 * @description：
 * @version: $
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {

    /**
     * 返回所有账户信息
     * @return
     */
    @Override
    public CommonResult<List<Account>> listAccount() {
        return new CommonResult<List<Account>>(this.list());
    }

    /**
     * 减账户
     * @param userId
     * @param money
     * @return
     */
    @Override
    public CommonResult decrease(Long userId, BigDecimal money) {
        Account account = this.getOne(Wrappers.<Account>query().lambda().eq(Account::getUserId, userId), false);
        this.update(new UpdateWrapper<Account>().lambda().eq(Account::getUserId, userId)
                .set(Account::getResidue,  account.getResidue().subtract(money))
                .set(Account::getUsed, account.getUsed().add(money)));
        return new CommonResult();
    }
}

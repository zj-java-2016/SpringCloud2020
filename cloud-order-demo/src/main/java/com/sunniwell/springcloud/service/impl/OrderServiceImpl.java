package com.sunniwell.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunniwell.springcloud.dao.OrderDao;
import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.entity.OrderInfo;
import com.sunniwell.springcloud.feign.AccountService;
import com.sunniwell.springcloud.feign.StorageService;
import com.sunniwell.springcloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 16:30
 * @description：
 * @version: $
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderInfo> implements OrderService {

    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    public CommonResult<List<OrderInfo>> listOrder() {
        return new CommonResult<>(this.list());
    }

    /**
     * 创建订单
     * @param order
     * @return
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public CommonResult create(OrderInfo order) {

        //新建订单
        this.save(order);
        //扣除金额
        accountService.decrease(order.getUserId(), order.getMoney());
        //减库存
        storageService.decrease(order.getProductId(), order.getCount());
        //完成订单
        this.update(new UpdateWrapper<OrderInfo>().lambda()
                .eq(OrderInfo::getUserId, order.getUserId())
                .eq(OrderInfo::getStatus, 0).set(OrderInfo::getStatus, 1));
        int i = 10/0;
        return new CommonResult();
    }
}

package com.sunniwell.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.entity.OrderInfo;

import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 16:29
 * @description：
 * @version: $
 */
public interface OrderService extends IService<OrderInfo> {

    /**
     * 查询所有订单
     * @return
     */
    CommonResult<List<OrderInfo>> listOrder();

    /**
     * 创建一个订单
     * @param order
     * @return
     */
    CommonResult create(OrderInfo order);
}

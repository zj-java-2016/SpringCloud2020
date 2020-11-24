package com.sunniwell.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunniwell.springcloud.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 16:29
 * @description：
 * @version: $
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderInfo> {
}

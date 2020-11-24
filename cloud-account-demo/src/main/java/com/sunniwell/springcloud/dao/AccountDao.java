package com.sunniwell.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunniwell.springcloud.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 14:36
 * @description：
 * @version: $
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {
}

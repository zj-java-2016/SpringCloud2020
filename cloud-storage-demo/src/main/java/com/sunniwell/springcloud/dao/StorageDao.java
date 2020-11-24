package com.sunniwell.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunniwell.springcloud.entity.Storage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 17:59
 * @description：
 * @version: $
 */
@Mapper
public interface StorageDao extends BaseMapper<Storage> {
}

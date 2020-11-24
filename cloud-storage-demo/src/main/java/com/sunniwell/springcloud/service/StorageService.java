package com.sunniwell.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.entity.Storage;

import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 17:59
 * @description：
 * @version: $
 */
public interface StorageService extends IService<Storage> {

    /**
     * 查询所有库存
     * @return
     */
    CommonResult<List<Storage>> listStorage();

    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    CommonResult decrease(Long productId, Integer count);
}

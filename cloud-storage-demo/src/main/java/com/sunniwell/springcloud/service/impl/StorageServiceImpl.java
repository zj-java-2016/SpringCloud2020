package com.sunniwell.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunniwell.springcloud.dao.StorageDao;
import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.entity.Storage;
import com.sunniwell.springcloud.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 18:00
 * @description：
 * @version: $
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageDao, Storage> implements StorageService {
    @Override
    public CommonResult<List<Storage>> listStorage() {
        return new CommonResult<>(this.list());
    }

    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    @Override
    public CommonResult decrease(Long productId, Integer count) {
        Storage storage = this.getOne(Wrappers.<Storage>query().lambda()
                .eq(Storage::getProductId, productId), false);
        this.update(new UpdateWrapper<Storage>().lambda().eq(Storage::getProductId, productId)
                .set(Storage::getResidue, storage.getResidue() - count)
                .set(Storage::getUsed, storage.getUsed() + count));
        return new CommonResult();
    }
}

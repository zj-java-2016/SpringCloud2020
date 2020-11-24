package com.sunniwell.springcloud.controller;

import com.sunniwell.springcloud.entity.CommonResult;
import com.sunniwell.springcloud.entity.Storage;
import com.sunniwell.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：zj
 * @date ：Created in 2020/11/4 18:00
 * @description：
 * @version: $
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @GetMapping
    public CommonResult<List<Storage>> listStorage(){
        return storageService.listStorage();
    }

    @PostMapping("/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId,
                                 @RequestParam("count") Integer count) {
        return storageService.decrease(productId, count);
    }
}

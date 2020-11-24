package com.sunniwell.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：zj
 * @date ：Created in 2020/10/27 15:07
 * @description：
 * @version: $
 */
@Data
@AllArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }

    public CommonResult(T data){
        this(200, "成功", data);
    }

    public CommonResult(){
        this(200, "成功", null);
    }
}

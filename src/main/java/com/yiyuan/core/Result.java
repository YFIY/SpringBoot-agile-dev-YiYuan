package com.yiyuan.core;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 * @author MoLi
 */
public class Result<T> {
    //状态码
    private int code;
    //提示
    private String message;
    //数据本体
    private T data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

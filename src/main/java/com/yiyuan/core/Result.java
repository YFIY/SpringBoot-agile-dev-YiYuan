package com.yiyuan.core;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 * @author MoLi
 */
public class Result<T> {
    //状态码
    private int code;
    //处理消息
    private String message;
    //数据本体
    private T data;
    //成功标识
    private Boolean success;
    //时间戳
    private long timestamp;



    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.timestamp = System.currentTimeMillis();
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

    public Boolean getSuccess() {
        return success;
    }

    public Result setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

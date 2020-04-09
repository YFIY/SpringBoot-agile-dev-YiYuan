package com.yiyuan.core;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 统一API响应结果封装
 * @author MoLi
 */
@ApiModel(value="接口返回对象", description="接口返回对象")
public class Result<T> {

    /**
     * 返回状态码
     */
    @ApiModelProperty(value = "返回状态码")
    private int code;
    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message;
    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T data;
    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private Boolean success;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
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

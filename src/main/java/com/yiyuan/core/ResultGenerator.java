package com.yiyuan.core;

/**
 * 响应结果生成工具
 * @author MoLi
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 不带数据的成功返回
     * @return Result
     */
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setSuccess(true);
    }

    /**
     * 带数据的成功返回
     * @author MoLi
     * @param  data 返回的数据
     * @return Result
     */
    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data)
                .setSuccess(true);
    }

    /**
     * 错误返回
     * @author MoLi
     * @param  message 错误提示
     * @return Result
     */
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setSuccess(false);
    }
}

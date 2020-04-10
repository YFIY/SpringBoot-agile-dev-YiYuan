package com.yiyuan.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 * @author MoLi
 */
public enum ResultCode {
    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //未认证（签名错误）
    UNauthorIZED(401),
    //权限不足
    INSUFFICIENTAUTHORITY(403),
    //接口不存在
    NOT_FOUND(404),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}

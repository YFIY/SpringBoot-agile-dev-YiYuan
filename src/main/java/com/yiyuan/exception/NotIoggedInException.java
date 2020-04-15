package com.yiyuan.exception;

/**
 * 登录状态异常 【例如登录失效】
 * [说明]调用此类会发送401响应，前端接收到401需跳转登录页
 * @author MoLi
 */
public class NotIoggedInException extends RuntimeException {

    public NotIoggedInException(String message) {
        super(message);
    }

    public NotIoggedInException(String message, Throwable cause) {
        super(message, cause);
    }

}

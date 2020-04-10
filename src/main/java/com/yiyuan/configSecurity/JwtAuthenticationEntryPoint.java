package com.yiyuan.configSecurity;

import com.alibaba.fastjson.JSON;
import com.yiyuan.config.WebMvcConfigurer;
import com.yiyuan.core.Result;
import com.yiyuan.core.ResultCode;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常信息响应类
 * [说明]当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此类发送401 响应
 * @author MoLi
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException authException) throws IOException {

        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException==null?"Unauthorized":authException.getMessage());

        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
        Result result = new Result();
        result.setCode(ResultCode.UNauthorIZED).setMessage("接口 [" + request.getRequestURI() + "] 您还未登录,无法访问").setSuccess(false);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

    }
}

package com.yiyuan.configSecurity;

import com.alibaba.fastjson.JSON;
import com.yiyuan.core.Result;
import com.yiyuan.core.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 授权异常信息响应类
 * [说明]当用户在没有授权的情况下访问受保护的REST资源时，将调用此类发送403 Forbidden响应
 * [注意]此类暂时没有用到,因为当发生权限不足时,WebMvcConfigurer类中的configureHandlerExceptionResolvers异常处理方法会将信息封装响应给前端
 * @author MoLi
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final Logger logger = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());

        //当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法发送403 Forbidden响应
        Result result = new Result();
        result.setCode(ResultCode.INSUFFICIENTAUTHORITY).setMessage("接口 [" + request.getRequestURI() + "] 您的权限不足,无法访问").setSuccess(false);
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

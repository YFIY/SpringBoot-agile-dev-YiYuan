package com.yiyuan.configSecurity;

import com.yiyuan.config.SecurityProperties;
import com.yiyuan.service.impl.OnlineUserService;
import com.yiyuan.utils.SpringContextHolder;
import com.yiyuan.vo.OnlineUserDto;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author perye
 * @email peryedev@gmail.com
 * @date 2019/12/13
 */
@Slf4j
public class TokenFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    TokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = resolveToken(httpServletRequest);
        String requestRri = httpServletRequest.getRequestURI();

        if(token != null){
            // 验证 token 是否存在
            OnlineUserDto onlineUserDto = null;
            try {
                SecurityProperties properties = SpringContextHolder.getBean(SecurityProperties.class);
                OnlineUserService onlineUserService = SpringContextHolder.getBean(OnlineUserService.class);
                onlineUserDto = onlineUserService.getOne(properties.getOnlineKey() + token);
            } catch (ExpiredJwtException e) {
                log.error(e.getMessage());
            }
            if (onlineUserDto != null && StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("将身份验证设置为的安全上下文 '{}', uri: {}", authentication.getName(), requestRri);
            } else {
                log.debug("找不到有效的JWT令牌, uri: {}", requestRri);
            }
        }



        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        SecurityProperties properties = SpringContextHolder.getBean(SecurityProperties.class);
        String bearerToken = request.getHeader(properties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
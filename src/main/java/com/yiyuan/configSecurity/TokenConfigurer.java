package com.yiyuan.configSecurity;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security拦截器注册配置
 */
public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * Token服务类
     */
    private final TokenProvider tokenProvider;

    /**
     * 要求初始化此类必须提供Token服务类[TokenProvider]
     */
    public TokenConfigurer(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    /**
     * Security拦截器注册配置方法
     */
    @Override
    public void configure(HttpSecurity http) {
        //获取Token过滤器放到
        TokenFilter customFilter = new TokenFilter(tokenProvider);
        //注册拦截器,将Token过滤器放到UsernamePasswordAuthenticationFilter后面执行
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

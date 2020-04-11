package com.yiyuan.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.yiyuan.annotation.AnonymousAccess;
import com.yiyuan.config.SecurityProperties;
import com.yiyuan.configSecurity.TokenProvider;
import com.yiyuan.entity.dto.CaptchaDto;
import com.yiyuan.service.CaptchaService;
import com.yiyuan.service.impl.OnlineUserService;
import com.yiyuan.utils.*;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.yiyuan.vo.AuthUserDto;
import com.yiyuan.vo.JwtUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 授权、根据token获取用户详细信息
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {

    @Value("${loginCode.expiration}")
    private Long expiration;

    @Value("${rsa.private_key}")
    private String privateKey;

    @Value("${single.login:false}")
    private Boolean singleLogin;

    /**
     * 验证码业务
     */
    private final CaptchaService captchaService;
    /**
     * Jwt参数配置类
     */
    private final SecurityProperties properties;
    /**
     * Redis工具类
     */
    private final RedisUtils redisUtils;
    /**
     * Security登录身份认证
     */
    private final UserDetailsService userDetailsService;
    /**
     * 在线用户业务类
     */
    private final OnlineUserService onlineUserService;
    /**
     * Token服务类
     */
    private final TokenProvider tokenProvider;
    /**
     * 认证管理器构建器
     */
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthorizationController(SecurityProperties properties, RedisUtils redisUtils, UserDetailsService userDetailsService, OnlineUserService onlineUserService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, CaptchaService captchaService) {
        this.properties = properties;
        this.redisUtils = redisUtils;
        this.userDetailsService = userDetailsService;
        this.onlineUserService = onlineUserService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.captchaService = captchaService;
    }

    /**
     * 登录授权
     * @param authUserDto
     * @return
     */
    @ApiOperation("登录授权")
    @AnonymousAccess
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUserDto, HttpServletRequest request){
        // 密码解密
        RSA rsa = new RSA(privateKey, null);
        String password = new String(rsa.decrypt(authUserDto.getPassword(), KeyType.PrivateKey));
        // 查询缓存中的验证码
        String code = (String) redisUtils.get(authUserDto.getUuid());
        // 清除缓存中的验证码
        redisUtils.del(authUserDto.getUuid());

        //TODO 为方便测试,暂先注销验证码校验
        /*if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(authUserDto.getCode()) || !authUserDto.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }*/

        // 封装用户名密码
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUserDto.getUsername(), password);
        //认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //认证成功,返回的Authentication对象赋予给当前的SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String token = tokenProvider.createToken(authentication);
        //用户信息封装
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        // 保存在线信息到缓存中
        onlineUserService.save(jwtUserDto, token, request);

        //如果开启了踢出功能
        if(singleLogin){
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUserDto.getUsername(),token);
        }
        // 返回 token 与 用户信息
        Map<String,Object> authInfo = new HashMap<String,Object>(2){{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
        return ResponseEntity.ok(authInfo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo(){
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("获取验证码")
    @AnonymousAccess
    @GetMapping(value = "/code")
    public ResponseEntity<Object> getCode() throws IOException, FontFormatException {
        // 类型 https://gitee.com/whvse/EasyCaptcha
//        根据数据库配置获取验证码类型
        CaptchaDto captcha = captchaService.getById(1L);
        SpecCaptcha specCaptcha = new SpecCaptcha(captcha.getWidth(), captcha.getHeight(), captcha.getLen());
        specCaptcha.setFont(new Font(captcha.getFontName(), captcha.getFontStyle(), captcha.getFontSize()));
        specCaptcha.setCharType(captcha.getType());
        String result = specCaptcha.text();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        // 保存
        redisUtils.set(uuid, result, expiration, TimeUnit.MINUTES);
        // 验证码信息
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("img", ((Captcha) specCaptcha).toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }

    @ApiOperation("退出登录")
    @AnonymousAccess
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        onlineUserService.logout(tokenProvider.getToken(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


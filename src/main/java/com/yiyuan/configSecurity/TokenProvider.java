package com.yiyuan.configSecurity;

import com.yiyuan.config.SecurityProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Token服务类
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    /**
     * Jwt参数配置类
     */
    private final SecurityProperties properties;
    private static final String AUTHORITIES_KEY = "auth";
    private Key key;

    /**
     * 要求初始化此类必须提供Jwt参数配置类[SecurityProperties]
     */
    public TokenProvider(SecurityProperties properties) {
        this.properties = properties;
    }


    /**
     * 初始化bean时自动执行的方法,因为实现了InitializingBean
     */
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + properties.getTokenValidityInSeconds());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /**
     * 获取认证
     */
    Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * 验证令牌是否正确
     * @param authToken 令牌字符串,不含前缀
     */
    boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("无效的JWT签名");
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            log.info("JWT令牌已过期");
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            log.info("不支持的JWT令牌");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            log.info("处理程序的JWT令牌压缩无效");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取请求中的Token令牌
     */
    public String getToken(HttpServletRequest request){
        //获取请求头中的令牌
        final String requestHeader = request.getHeader(properties.getHeader());
        //如果令牌不为空并且令牌前缀合法
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            //返回不带前缀的令牌
            return requestHeader.substring(7);
        }
        return null;
    }
}

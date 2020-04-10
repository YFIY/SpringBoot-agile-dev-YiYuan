package com.yiyuan.config;

import com.yiyuan.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 方法级权限控制
 * [说明] 被@PreAuthorize("@dokit.check('xx:xx')")注解的方法都会被纳入权限控制,此类用于自定义拦截规则,如果用户是admin则直接放行,如果不是admin则看该用户所拥有的角色是否有该方法的权限
 * @author MoLi
 */
@Service(value = "dokit")
public class DokitPermissionConfig {

    /**
     * 自定义权限拦截规则
     * @author MoLi
     */
    public Boolean check(String ...permissions){
        List<String> dokitPermissions = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return dokitPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(dokitPermissions::contains);
    }

}

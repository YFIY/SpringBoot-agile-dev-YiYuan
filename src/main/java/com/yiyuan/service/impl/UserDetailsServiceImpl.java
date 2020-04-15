package com.yiyuan.service.impl;

import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.exception.ServiceException;
import com.yiyuan.service.RoleService;
import com.yiyuan.service.UserService;
import com.yiyuan.vo.JwtUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Security登录身份认证
 * [说明]用户登录时如果提供的账号密码正确,则认证通过
 * @author MoLi
 */
@Service("userDetailsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;

    public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @Override
    public JwtUserDto loadUserByUsername(String username){

        //通过登录名从数据源获取用户是否存在
        UserDto user = userService.findByName(username);
        if (user == null) {
            throw new ServiceException("账号不存在");
        } else {
            if (!user.getEnabled()) {
                throw new ServiceException("账号未激活");
            }

            //获取该用户拥有的权限
            List<GrantedAuthority> grantedAuthorities = roleService.mapToGrantedAuthorities(user);

            return new JwtUserDto(
                    user,
                    grantedAuthorities
            );
        }
    }
}


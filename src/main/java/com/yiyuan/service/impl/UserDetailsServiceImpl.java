package com.yiyuan.service.impl;

import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.exception.BadRequestException;
import com.yiyuan.service.RoleService;
import com.yiyuan.service.UserService;
import com.yiyuan.vo.JwtUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//疑似权限控制所查询数据库用户的类
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

        UserDto user = userService.findByName(username);
        if (user == null) {
            throw new BadRequestException("账号不存在");
        } else {
            if (!user.getEnabled()) {
                throw new BadRequestException("账号未激活");
            }
            return new JwtUserDto(
                    user,
                    roleService.mapToGrantedAuthorities(user)
            );
        }
    }
}


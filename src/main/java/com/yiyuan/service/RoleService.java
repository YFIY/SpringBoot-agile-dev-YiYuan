package com.yiyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.Role;
import com.yiyuan.entity.dto.RoleDto;
import com.yiyuan.entity.dto.RoleSmallDto;
import com.yiyuan.entity.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 获取用户权限信息
     * @param user 用户信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto user);

    /**
     * 根据用户ID查询角色数据
     * @param id 用户ID
     */
    List<RoleSmallDto> findByUsersId(Long id);

    /**
     * 根据角色查询角色级别
     * @param roles 角色信息
     */
    Integer findByRoles(Set<Role> roles);
}

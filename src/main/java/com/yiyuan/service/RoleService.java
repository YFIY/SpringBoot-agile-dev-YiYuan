package com.yiyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.dto.RoleDto;
import com.yiyuan.entity.dto.RoleSmallDto;
import com.yiyuan.entity.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface RoleService {

    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto user);

    /**
     * 根据用户ID查询
     * @param id 用户ID
     * @return /
     */
    List<RoleSmallDto> findByUsersId(Long id);

}

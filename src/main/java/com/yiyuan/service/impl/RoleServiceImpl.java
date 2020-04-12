package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.RoleDao;
import com.yiyuan.dao.RoleDtoDao;
import com.yiyuan.entity.Menu;
import com.yiyuan.entity.Role;
import com.yiyuan.entity.dto.RoleDto;
import com.yiyuan.entity.dto.RoleSmallDto;
import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.service.RoleRepository;
import com.yiyuan.service.RoleService;
import com.yiyuan.utils.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleRepository roleRepository,RoleDao roleDao) {
        this.roleRepository = roleRepository;
        this.roleDao = roleDao;
    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        //获取该用户的角色数据
        Set<Role> roles = roleRepository.findByUsers_Id(user.getId());
        //取出角色标识
        Set<String> permissions = roles.stream().filter(role -> StringUtils.isNotBlank(role.getPermission())).map(Role::getPermission).collect(Collectors.toSet());
        //取出具体所拥有的权限
        permissions.addAll(
                roles.stream().flatMap(role -> role.getMenus().stream())
                        .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                        .map(Menu::getPermission).collect(Collectors.toSet())
        );

        //将所有的权限封装成security权限类型
        List<GrantedAuthority> grantedAuthorityList = permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return grantedAuthorityList;
    }

    @Override
    public List<RoleSmallDto> findByUsersId(Long id) {
        //根据用户ID查询该用户所有的角色
        Set<RoleSmallDto> roleList = roleDao.findRoleList(id);
        //set转list
        List<RoleSmallDto> result = new ArrayList<>(roleList);

        return result;
    }
}

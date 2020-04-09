package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.RoleDtoDao;
import com.yiyuan.entity.Menu;
import com.yiyuan.entity.Role;
import com.yiyuan.entity.dto.RoleDto;
import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.service.RoleRepository;
import com.yiyuan.service.RoleService;
import com.yiyuan.utils.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleDtoDao, RoleDto> implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        Set<Role> roles = roleRepository.findByUsers_Id(user.getId());
        Set<String> permissions = roles.stream().filter(role -> StringUtils.isNotBlank(role.getPermission())).map(Role::getPermission).collect(Collectors.toSet());
        permissions.addAll(
                roles.stream().flatMap(role -> role.getMenus().stream())
                        .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                        .map(Menu::getPermission).collect(Collectors.toSet())
        );
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

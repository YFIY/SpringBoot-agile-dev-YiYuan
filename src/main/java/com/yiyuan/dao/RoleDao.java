package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.Role;
import com.yiyuan.entity.dto.CaptchaDto;
import com.yiyuan.entity.dto.RoleSmallDto;

import java.util.Set;

public interface RoleDao extends BaseMapper<Role> {

    //根据用户ID查询该用户所有的角色,两表联查
    Set<RoleSmallDto> findRoleList (Long userId);

}

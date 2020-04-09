package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.Role;

import java.util.Set;

public interface RoleRepositoryDao extends BaseMapper<Role> {

    /**
     * 根据用户ID查询
     * @param id 用户ID
     * @return
     */
    Set<Role> findByUsers_Id(Long id);

}

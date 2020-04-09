package com.yiyuan.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.Role;

import java.util.Set;

@SuppressWarnings("all")//抑制警告
public interface RoleRepository extends IService<Role> {

    /**
     * 根据用户ID查询
     * @param id 用户ID
     * @return
     */
    Set<Role> findByUsers_Id(Long id);


}


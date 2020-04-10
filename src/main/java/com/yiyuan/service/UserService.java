package com.yiyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.User;
import com.yiyuan.entity.dto.UserDto;

import java.util.Set;

/**
 * 用户业务
 * @author MoLi
 */
public interface UserService extends IService<UserDto> {


    /**
     * 根据用户名获取DTO用户数据,多表查询
     */
    UserDto findByName(String username);

    /**
     * 根据角色ID获取角色下的所有用户,多表查询
     */
    Set<User> findRoleIdByUser(Long roleId);

}

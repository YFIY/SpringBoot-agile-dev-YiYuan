package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.User;
import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.entity.sql.UserSqlEntity;

import java.util.Set;

public interface UserDao extends BaseMapper<UserSqlEntity> {

    /**
     * 根据用户名字获取该用户Dto信息,包括角色、岗位、部门数据
     */
    UserDto findByName(String username);

    /**
     * 根据用户名字获取该用户Dto信息,包括角色、岗位、部门数据 2
     */
    UserDto findByName2(String username);

    /**
     * 根据角色ID获取该角色ID下的所有用户信息
     */
    Set<User> findRoleIdByUser(Long roleId);


}

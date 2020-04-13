package com.yiyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.User;
import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.entity.sql.UserSqlEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 用户业务
 * @author MoLi
 */
public interface UserService extends IService<UserSqlEntity> {


    /**
     * 根据用户名获取DTO用户数据,多表查询
     */
    UserDto findByName(String username);

    /**
     * 根据角色ID获取角色下的所有用户,多表查询
     */
    Set<User> findRoleIdByUser(Long roleId);

    /**
     * 导出数据
     *
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException;

}

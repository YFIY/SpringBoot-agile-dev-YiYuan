package com.yiyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.User;
import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.entity.sql.UserSqlEntity;
import com.yiyuan.query.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

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
     * 根据用户名获取DTO用户数据,多表查询 优化版
     */
    UserDto findByName2(String username);

    /**
     * 根据角色ID获取角色下的所有用户,多表查询
     */
    Set<User> findRoleIdByUser(Long roleId);


    /**
     * 动态条件分页获取符合条件的用户数据集合
     */
    Object queryAll(UserQueryCriteria criteria, Pageable pageable);
}

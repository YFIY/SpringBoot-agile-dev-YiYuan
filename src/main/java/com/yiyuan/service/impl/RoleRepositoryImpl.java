package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.*;
import com.yiyuan.entity.Menu;
import com.yiyuan.entity.Role;
import com.yiyuan.entity.User;
import com.yiyuan.service.RoleRepository;
import com.yiyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Set;

@Service
@Transactional
public class RoleRepositoryImpl extends ServiceImpl<RoleRepositoryDao, Role> implements RoleRepository {

    @Autowired
    RoleRepositoryDao roleRepositoryDao;
    @Autowired
    UserService userService;
    @Autowired
    MenuDao menuDao;


    @Override
    public Set<Role> findByUsers_Id(Long id) {

        //获取该用户所有角色,两表联查
        Set<Role> rolesSet = roleRepositoryDao.findByUsers_Id(id);

        //遍历所有角色
        Iterator<Role> iterator = rolesSet.iterator();
        while (iterator.hasNext()) {
            //获取单个角色
            Role role = iterator.next();

            //获取该角色下的所有菜单
            Set<Menu> menuSet = menuDao.selectMenusByRoleId(role.getId());
            //注入
            role.setMenus(menuSet);

        }

        return rolesSet;
    }
}

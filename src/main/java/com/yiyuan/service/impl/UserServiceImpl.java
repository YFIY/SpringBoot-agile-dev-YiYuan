package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.*;
import com.yiyuan.entity.Role;
import com.yiyuan.entity.User;
import com.yiyuan.entity.UserAvatar;
import com.yiyuan.entity.dto.DeptSmallDto;
import com.yiyuan.entity.dto.JobSmallDto;
import com.yiyuan.entity.dto.RoleSmallDto;
import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Set;

/**
 * 用户业务
 * @author MoLi
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, UserDto> implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserAvatarDao userAvatarDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    JobDao jobDao;
    @Autowired
    DeptDao deptDao;

    @Override
    public UserDto findByName(String username) {

        //获取用户表中的数据
        UserDto userDto = userDao.findByName(username);

        //获取用户头像表中的数据
        UserAvatar userAvatar = userAvatarDao.selectById(userDto.getAvatarId());
        if(userAvatar!=null){
            //头像文件名注入用户DTO
            userDto.setAvatar(userAvatar.getRealName());
        }



        //获取角色表中用户的角色
        Set<RoleSmallDto> roles = roleDao.findRoleList(userDto.getId());
        //角色数据注入用户DTO
        userDto.setRoles(roles);

        //获取job表中的数据
        JobSmallDto jobSmallDto = jobDao.findJobById(userDto.getJobId());
        //job注入
        userDto.setJob(jobSmallDto);

        //获取dept表中的数据
        DeptSmallDto deptSmallDto = deptDao.findDeptSmallById(userDto.getDeptId());
        //dept注入
        userDto.setDept(deptSmallDto);


        return userDto;
    }

    @Override
    public Set<User> findRoleIdByUser(Long roleId) {

        //获取该角色ID下的所有用户
        Set<User> userSet = userDao.findRoleIdByUser(roleId);

        //遍历所有角色
        Iterator<User> iterator = userSet.iterator();
        while (iterator.hasNext()) {
            //获取单个用户
            User user = iterator.next();




        }


        return userSet;
    }


}

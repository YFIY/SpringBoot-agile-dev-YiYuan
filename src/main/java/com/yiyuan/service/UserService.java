package com.yiyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.dto.UserDto;

/**
 * 用户业务
 * @author MoLi
 */
public interface UserService extends IService<UserDto> {


    //获取用户数据,多表查询
    UserDto findByName(String username);


}

package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.dto.CaptchaDto;
import com.yiyuan.entity.dto.UserDto;

public interface UserDao extends BaseMapper<UserDto> {

    UserDto findByName(String username);

}

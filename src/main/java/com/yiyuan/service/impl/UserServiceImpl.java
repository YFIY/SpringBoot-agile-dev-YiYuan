package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.CaptchaDao;
import com.yiyuan.dao.UserDao;
import com.yiyuan.entity.dto.CaptchaDto;
import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.service.CaptchaService;
import com.yiyuan.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务
 * @author MoLi
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, UserDto> implements UserService {


    @Override
    public UserDto findByName(String username) {
        return this.baseMapper.findByName(username);
    }



}

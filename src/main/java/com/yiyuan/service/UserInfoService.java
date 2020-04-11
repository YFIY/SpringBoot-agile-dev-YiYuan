package com.yiyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.UserInfoEntity;

/**
 * 演示类 学生用户业务接口
 * @author MoLi
 */
public interface UserInfoService extends IService<UserInfoEntity> {

    /**
     * 查询大于该分数的学生
     * @author MoLi
     * @CreateTime 2019/6/9 14:27
     * @param  page  分页参数
     * @param  fraction  分数
     * @return IPage<UserInfoEntity> 分页数据
     */
    IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction);

}

package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yiyuan.entity.UserInfoEntity;

/**
 * 用户信息DAO
 * @author MoLi
 */
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
    /**
     * 查询大于该分数的学生
     * @author MoLi
     * @CreateTime 2019/6/9 14:28
     * @param  page  分页参数
     * @param  fraction  分数
     * @return IPage<UserInfoEntity> 分页数据
     */
    IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction);
}

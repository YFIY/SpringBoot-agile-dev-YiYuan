package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yiyuan.entity.UserInfoEntity;

/**
 * @Description 用户信息DAO
 * @Author MoLi
 */
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
    /**
     * 查询大于该分数的学生
     * @Author MoLi
     * @CreateTime 2019/6/9 14:28
     * @Param  page  分页参数
     * @Param  fraction  分数
     * @Return IPage<UserInfoEntity> 分页数据
     */
    IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction);
}

package com.yiyuan.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.UserInfoDao;
import com.yiyuan.entity.UserInfoEntity;
import com.yiyuan.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 用户业务实现
 * @author MoLi
 * @CreateTime 2019/6/8 16:26
 */
@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {

    /**
     * 查询大于该分数的学生
     * @author MoLi
     * @CreateTime 2019/6/9 14:27
     * @param  page  分页参数
     * @param  fraction  分数
     * @return IPage<UserInfoEntity> 分页数据
     */
    @Override
    public IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction) {
        return this.baseMapper.selectUserInfoByGtFraction(page,fraction);
    }
}

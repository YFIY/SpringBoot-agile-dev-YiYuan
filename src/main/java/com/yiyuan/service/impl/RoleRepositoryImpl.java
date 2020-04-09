package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.RoleDao;
import com.yiyuan.dao.RoleDtoDao;
import com.yiyuan.dao.RoleRepositoryDao;
import com.yiyuan.entity.Role;
import com.yiyuan.service.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class RoleRepositoryImpl extends ServiceImpl<RoleRepositoryDao, Role> implements RoleRepository {
    @Override
    public Set<Role> findByUsers_Id(Long id) {
        return this.baseMapper.findByUsers_Id(id);
    }
}

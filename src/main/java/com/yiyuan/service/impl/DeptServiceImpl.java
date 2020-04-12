package com.yiyuan.service.impl;

import com.yiyuan.entity.Dept;
import com.yiyuan.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DeptServiceImpl implements DeptService {
    @Override
    public Set<Dept> findByRoleIds(Long id) {
        return null;
    }

    @Override
    public List<Dept> findByPid(Long id) {
        return null;
    }
}

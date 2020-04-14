package com.yiyuan.service.impl;

import com.yiyuan.dao.DeptDao;
import com.yiyuan.entity.Dept;
import com.yiyuan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptDao deptDao;

    /**
     * 根据角色ID获取部门数据
     */
    @Override
    public Set<Dept> findByRoleIds(Long roleId) {

        //根据角色ID查询角色下的部门集合，两表查询
        Set<Dept> deptSet = deptDao.findByRoleIds(roleId);


        return deptSet;
    }

    /**
     * 根据上级部门ID获取部门数据
     */
    @Override
    public List<Dept> findByPid(Long deptId) {

        List<Dept> deptList = deptDao.findByPid(deptId);

        return deptList;
    }
}

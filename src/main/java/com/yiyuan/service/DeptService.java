package com.yiyuan.service;


import com.yiyuan.entity.Dept;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface DeptService {

    /**
     * 根据角色ID获取部门数据
     */
    Set<Dept> findByRoleIds(Long roleId);

    /**
     * 根据上级部门ID获取部门数据
     */
    List<Dept> findByPid(Long deptId);
}

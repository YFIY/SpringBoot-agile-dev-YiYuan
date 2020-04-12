package com.yiyuan.service;


import com.yiyuan.entity.Dept;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface DeptService {
    
    Set<Dept> findByRoleIds(Long id);

    List<Dept> findByPid(Long id);
}

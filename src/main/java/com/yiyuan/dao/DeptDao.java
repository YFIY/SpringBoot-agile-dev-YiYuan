package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.Dept;
import com.yiyuan.entity.dto.DeptSmallDto;
import com.yiyuan.entity.sql.DeptSqlEntity;

import java.util.List;
import java.util.Set;

/**
 * 部门表的数据访问层
 */
public interface DeptDao extends BaseMapper<DeptSqlEntity> {

    /**
     * 根据部门ID查询一条ID和名称数据
     */
    DeptSmallDto findDeptSmallById (Long id);

    /**
     * 根据角色ID查询角色下的部门集合，两表查询
     */
    Set<Dept> findByRoleIds(Long roleId);

    /**
     * 根据上级部门ID获取部门数据
     */
    List<Dept> findByPid(Long deptId);
}

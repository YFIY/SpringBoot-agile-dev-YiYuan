package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.dto.DeptSmallDto;
import com.yiyuan.entity.sql.DeptSqlEntity;

//job表的数据访问层
public interface DeptDao extends BaseMapper<DeptSqlEntity> {

    DeptSmallDto findDeptSmallById (Long id);

}

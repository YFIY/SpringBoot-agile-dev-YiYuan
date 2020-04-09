package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.Role;
import com.yiyuan.entity.dto.JobSmallDto;
import com.yiyuan.entity.dto.RoleSmallDto;

import java.util.Set;

//job表的数据访问层
public interface JobDao {

    //根据ID查询
    JobSmallDto findJobById (Long id);

}

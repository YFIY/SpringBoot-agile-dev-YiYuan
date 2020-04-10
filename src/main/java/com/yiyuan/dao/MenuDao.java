package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.Menu;

import java.util.Set;

public interface MenuDao extends BaseMapper<Menu> {

    Set<Menu> selectMenusByRoleId (Long roleId);

}

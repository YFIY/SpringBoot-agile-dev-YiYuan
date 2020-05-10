package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.sql.CargoBillSqlEntity;

public interface CargoBillDao extends BaseMapper<CargoBillSqlEntity> {

    /**
     * 动态条件分页查询欠费单数据
     */
    IPage<CargoBillSqlEntity> getListMapPage(IPage<CargoBillSqlEntity> page, CargoBillSqlEntity queryModel);

}

package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.sql.CargoBillSqlEntity;
import com.yiyuan.entity.vo.CargoBillVoEntity;

public interface CargoBillDao extends BaseMapper<CargoBillSqlEntity> {

    /**
     * 动态条件分页查询欠费单数据
     */
    IPage<CargoBillVoEntity> getListMapPage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel);

}

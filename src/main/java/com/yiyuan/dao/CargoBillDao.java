package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.dto.SummaryStatisticsDTO;
import com.yiyuan.entity.sql.CargoBillSqlEntity;
import com.yiyuan.entity.vo.CargoBillVoEntity;
import com.yiyuan.entity.vo.SummaryStatisticsVO;

import java.util.List;

public interface CargoBillDao extends BaseMapper<CargoBillSqlEntity> {

    /**
     * 动态条件分页查询欠费单数据
     */
    IPage<CargoBillVoEntity> getListMapPage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel);

    /**
     * 查询最新一条的数据，用于快速添加功能
     */
    IPage<CargoBillVoEntity> getUnloadingTonnage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel);
    /**
     * 统计汇总-
     */
   List<CargoBillVoEntity>  summaryStatistics(SummaryStatisticsDTO summaryStatisticsDTO);
}

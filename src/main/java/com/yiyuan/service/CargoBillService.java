package com.yiyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.dao.CargoBillDao;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.dto.SummaryStatisticsDTO;
import com.yiyuan.entity.sql.CargoBillSqlEntity;
import com.yiyuan.entity.vo.CargoBillVoEntity;
import com.yiyuan.entity.vo.SummaryStatisticsVO;

/**
 * 货运单功能业务接口
 * @author MoLi
 */
public interface CargoBillService extends IService<CargoBillSqlEntity> {

    /**
     * 动态条件分页查询
     */
    public IPage<CargoBillVoEntity> getListMapPage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel);

    /**
     * 查询最新一条的数据，用于快速添加功能
     */
    public IPage<CargoBillVoEntity> getUnloadingTonnage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel);

    /**
     * 统计汇总
     *
     * @author MoLi
     */
    SummaryStatisticsVO summaryStatistics(SummaryStatisticsDTO model);
}

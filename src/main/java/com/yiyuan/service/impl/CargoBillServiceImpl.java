package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.CargoBillDao;
import com.yiyuan.dao.CfgDao;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.dto.SummaryStatisticsDTO;
import com.yiyuan.entity.sql.CargoBillSqlEntity;
import com.yiyuan.entity.vo.CargoBillVoEntity;
import com.yiyuan.entity.vo.SummaryStatisticsVO;
import com.yiyuan.service.CargoBillService;
import com.yiyuan.service.CfgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CargoBillServiceImpl extends ServiceImpl<CargoBillDao, CargoBillSqlEntity> implements CargoBillService {

    /**
     * 动态条件分页查询
     */
    @Override
    public IPage<CargoBillVoEntity> getListMapPage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel){
        return this.baseMapper.getListMapPage(page,queryModel);
    }

    /**
     * 查询最新一条的数据，用于快速添加功能
     */
    @Override
    public IPage<CargoBillVoEntity> getUnloadingTonnage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel){
        return this.baseMapper.getUnloadingTonnage(page,queryModel);
    }

    /**
     * 统计汇总
     *
     * @author MoLi
     */
    @Override
    public SummaryStatisticsVO summaryStatistics(SummaryStatisticsDTO model) {
        System.out.println(model);
        System.out.println(model);
        return null;
    }

}

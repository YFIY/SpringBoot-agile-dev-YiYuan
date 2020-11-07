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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CargoBillServiceImpl extends ServiceImpl<CargoBillDao, CargoBillSqlEntity> implements CargoBillService {

    /**
     * 动态条件分页查询
     */
    @Override
    public IPage<CargoBillVoEntity> getListMapPage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel) {
        return this.baseMapper.getListMapPage(page, queryModel);
    }

    /**
     * 查询最新一条的数据，用于快速添加功能
     */
    @Override
    public IPage<CargoBillVoEntity> getUnloadingTonnage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel) {
        return this.baseMapper.getUnloadingTonnage(page, queryModel);
    }

    /**
     * 统计汇总
     *
     * @author MoLi
     */
    @Override
    public SummaryStatisticsVO summaryStatistics(SummaryStatisticsDTO model) {

        //截至时间加一天
        model.setEndTime(new Date(model.getEndTime().getTime() + (long) ((24 * 60 * 60 * 1000) - 1)));

        List<CargoBillVoEntity> cargoBillVoEntities = this.baseMapper.summaryStatistics(model);
        //总装车吨数
        BigDecimal totalTonnage = new BigDecimal(0.0);
        //总卸车吨数
        BigDecimal totalUnloadingTonnage = new BigDecimal(0.0);
        //总销售金额
        BigDecimal totalSalesAmount = new BigDecimal(0.0);
        //总净利润
        BigDecimal totalNetProfit = new BigDecimal(0.0);

        //成本价
        BigDecimal costPrice = new BigDecimal(0.0);
        //净利润
        BigDecimal netProfit = new BigDecimal(0.0);
        for (CargoBillVoEntity entity : cargoBillVoEntities) {
            //装车吨数加运算
            totalTonnage = totalTonnage.add(entity.getGoodsNumber());
            if (entity.getUnloadingTonnage() != null) {
                //卸车吨数加运算
                totalUnloadingTonnage = totalUnloadingTonnage.add(entity.getUnloadingTonnage());
                //销售金额 = 卸车吨数 * 销售价/吨
                totalSalesAmount = totalSalesAmount.add(entity.getUnloadingTonnage().multiply(entity.getSellingPrice()));
                // 成本价 = 装车吨数 *（单价/吨 + 运费/吨）
                costPrice = entity.getGoodsNumber().multiply(entity.getGoodsUnitPrice().add(entity.getGoodsFreight()));
                // 净利润 = 销售金额 - 成本价
                netProfit = entity.getUnloadingTonnage().multiply(entity.getSellingPrice()).subtract(costPrice);
                //总利润加运算
                totalNetProfit = totalNetProfit.add(netProfit);
            }
        }
        SummaryStatisticsVO summaryStatisticsVO = new SummaryStatisticsVO();
        //总装车吨数
        summaryStatisticsVO.setTotalTonnage(totalTonnage);
        //总卸车吨数
        summaryStatisticsVO.setTotalUnloadingTonnage(totalUnloadingTonnage);
        //总销售金额
        summaryStatisticsVO.setTotalSalesAmount(totalSalesAmount);
        //总净利润
        summaryStatisticsVO.setTotalNetProfit(totalNetProfit);

        return summaryStatisticsVO;
    }

}

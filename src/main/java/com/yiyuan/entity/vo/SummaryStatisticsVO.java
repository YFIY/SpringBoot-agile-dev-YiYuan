package com.yiyuan.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummaryStatisticsVO {
    /**
     * 总装车吨数
     */
    private BigDecimal totalTonnage;
    /**
     * 总卸车吨数
     */
    private BigDecimal totalUnloadingTonnage;
    /**
     * 总销售金额
     */
    private BigDecimal totalSalesAmount;
    /**
     * 总净利润
     */
    private BigDecimal totalNetProfit;
}

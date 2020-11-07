package com.yiyuan.entity.vo;

import lombok.Data;

@Data
public class SummaryStatisticsVO {
    /**
     * 总装车吨数
     */
    private String totalTonnage;
    /**
     * 总卸车吨数
     */
    private String totalUnloadingTonnage;
    /**
     * 总销售金额
     */
    private String totalSalesAmount;
    /**
     * 总净利润
     */
    private String totalNetProfit;
}

package com.yiyuan.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SummaryStatisticsDTO {
    /**
     * 装车地
     */
    private String departureLocation;
    /**
     * 卸车地
     */
    private String arrivedLocation;
    /**
     * 开始时间
     */
    private Date startingTime;
    /**
     * 结束时间
     */
    private Date endTime;
}

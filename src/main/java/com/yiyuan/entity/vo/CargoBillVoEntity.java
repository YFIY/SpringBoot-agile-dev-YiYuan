package com.yiyuan.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 货运单页面视图模型
 * @author MoLi
 */
@Data
public class CargoBillVoEntity {

    /**
     * 货运单ID
     */
    private String id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 购进时间
     */
    private Date goodsTime;
    /**
     * 出发地点
     */
    private String departureLocation;
    /**
     * 到达地点
     */
    private String arrivedLocation;
    /**
     * 货物名称
     */
    private String goodsName;
    /**
     * 货物规格
     */
    private String goodsSpecification;
    /**
     * 装车吨数
     */
    private BigDecimal goodsNumber;
    /**
     * 卸车吨数
     */
    private BigDecimal unloadingTonnage;
    /**
     * 货物单位
     */
    private Long goodsUnit;
    /**
     * 料款/吨
     */
    private BigDecimal goodsUnitPrice;
    /**
     * 运费/吨
     */
    private BigDecimal goodsFreight;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 运单备注
     */
    private String goodsRemarks;
    /**
     * 是否出货 0:未出货 1:出货
     */
    private Integer whetherToShip;
    /**
     * 出货时间
     */
    private Date shippingTime;
    /**
     * 销售价/吨
     */
    private BigDecimal sellingPrice;
}

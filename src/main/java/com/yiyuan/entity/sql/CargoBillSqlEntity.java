package com.yiyuan.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 货运单数据库模型(表名:t_cargo_bill)
 * @author MoLi
 */
@Data
@TableName("t_cargo_bill")//表名
public class CargoBillSqlEntity {

    /**
     * 货运单ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 装车时间
     */
    private Date goodsTime;
    /**
     * 装车地
     */
    private String departureLocation;
    /**
     * 卸车地
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
     * 单价/吨
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
    /**
     * 是否删除 0:正常 1:删除
     */
    private Integer deleteOrNot;
}

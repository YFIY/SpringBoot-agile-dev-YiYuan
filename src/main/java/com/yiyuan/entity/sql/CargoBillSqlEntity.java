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
     * 货物数量
     */
    private Long goodsNumber;
    /**
     * 货物单位
     */
    private Long goodsUnit;
    /**
     * 货物单价
     */
    private BigDecimal goodsUnitPrice;
    /**
     * 货物运费
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

}

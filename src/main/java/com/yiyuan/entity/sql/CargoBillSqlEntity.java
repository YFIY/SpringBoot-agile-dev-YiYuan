package com.yiyuan.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 货运单数据库模型(表名:t_cargo_bill)
 * @author MoLi
 */
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
     * 吨数
     */
    private Long goodsNumber;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getGoodsTime() {
        return goodsTime;
    }

    public void setGoodsTime(Date goodsTime) {
        this.goodsTime = goodsTime;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivedLocation() {
        return arrivedLocation;
    }

    public void setArrivedLocation(String arrivedLocation) {
        this.arrivedLocation = arrivedLocation;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Long getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Long goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Long getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(Long goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public BigDecimal getGoodsUnitPrice() {
        return goodsUnitPrice;
    }

    public void setGoodsUnitPrice(BigDecimal goodsUnitPrice) {
        this.goodsUnitPrice = goodsUnitPrice;
    }

    public BigDecimal getGoodsFreight() {
        return goodsFreight;
    }

    public void setGoodsFreight(BigDecimal goodsFreight) {
        this.goodsFreight = goodsFreight;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getGoodsRemarks() {
        return goodsRemarks;
    }

    public void setGoodsRemarks(String goodsRemarks) {
        this.goodsRemarks = goodsRemarks;
    }

    public Integer getWhetherToShip() {
        return whetherToShip;
    }

    public void setWhetherToShip(Integer whetherToShip) {
        this.whetherToShip = whetherToShip;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}

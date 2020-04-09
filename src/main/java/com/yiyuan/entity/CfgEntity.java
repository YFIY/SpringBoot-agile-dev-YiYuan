package com.yiyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * 全局系统配置参数模型类
 * @author MoLi
 */
@Data
@TableName("t_sys_cfg")//表名
public class CfgEntity extends BaseEntity {

    /**
     * 参数名
     */
    private String cfgName;
    /**
     * 参数值
     */
    private String cfgValue;
    /**
     * 备注
     */
    private String cfgDesc;

}

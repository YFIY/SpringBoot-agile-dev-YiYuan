package com.yiyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description 全局系统配置参数模型类
 * @Author MoLi
 */
@Data
@TableName("t_sys_cfg")//@TableName中的值对应着表名
public class CfgEntity extends BaseEntity {

    //参数名
    private String cfgName;
    //参数值
    private String cfgValue;
    //备注
    private String cfgDesc;

}

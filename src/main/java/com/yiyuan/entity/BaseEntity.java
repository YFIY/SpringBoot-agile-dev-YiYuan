package com.yiyuan.entity;

import lombok.Data;

import java.util.Date;

/**
 * 后端业务实体基类
 * @author MoLi
 */
@Data
public abstract class BaseEntity {

    //ID
    private Long id;
    //创建时间/注册时间
    private Date createTime;
    //创建人
    private Long createBy;
    //最后更新时间
    private Date modifyTime;
    //最后更新人
    private Long modifyBy;

}

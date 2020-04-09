package com.yiyuan.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@TableName("role")//表名
public class RoleDto implements Serializable {

    private Long id;

    private String name;

    private String dataScope;

    private Integer level;

    private String remark;

    private String permission;

    private Set<MenuDto> menus;

    private Set<DeptDto> depts;

    private Timestamp createTime;

}

package com.yiyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@TableName("menu")//@TableName中的值对应着表名
public class Menu implements Serializable {

    private Long id;

    private String name;

    private Long sort = 999L;

    private String path;

    private String component;

    /** 类型，目录、菜单、按钮 */
    private Integer type;

    /** 权限 */
    private String permission;


    private String componentName;

    private String icon;

    private Boolean cache;

    private Boolean hidden;

    /** 上级菜单ID */
    private Long pid;

    /** 是否为外链 true/false */
    private Boolean iFrame;

    @JsonIgnore
    private Set<Role> roles;



}


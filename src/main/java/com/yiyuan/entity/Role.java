package com.yiyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 角色
 */
@TableName("role")//@TableName中的值对应着表名
@Getter
@Setter
public class Role implements Serializable {

    private Long id;

    private String name;

    private String dataScope = "本级";

    private Integer level = 3;

    private String remark;

    // 权限
    private String permission;

    private Set<User> users;

    private Set<Menu> menus;

    private Set<Dept> depts;



    private Timestamp createTime;

}


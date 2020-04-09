package com.yiyuan.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@TableName("user")//表名
public class UserDto implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String username;

    private String nickName;

    private String sex;

    //头像表的ID
    private Long avatarId;

    //头像图片路径
    private String avatar;

    private String email;

    private String phone;

    private Boolean enabled;

    @JsonIgnore
    private String password;

    private Date lastPasswordResetTime;

    private Set<RoleSmallDto> roles;

    //job表的ID
    private Long jobId;

    private JobSmallDto job;

    //TODO 此数据还未注入
    private DeptSmallDto dept;

    private Long deptId;

    private Timestamp createTime;
}


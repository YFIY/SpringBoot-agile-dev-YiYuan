package com.yiyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@TableName("user")//表名
public class User implements Serializable {

    private Long id;

    private String username;

    /** 用户昵称 */
    @NotBlank
    private String nickName;

    /** 性别 */
    private String sex;

    //头像表的ID
    private Long avatarId;
    private UserAvatar userAvatar;

    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private Boolean enabled;

    private String password;

    private Date lastPasswordResetTime;

    private Set<Role> roles;

    private Job job;

    private Dept dept;

    private Timestamp createTime;


}

package com.yiyuan.entity.sql;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//用户表模型
@Data
@TableName("user")//表名
public class UserSqlEntity {

    private Long id;
    private Long avatarId;
    private String email;
    private Long enabled;
    private String password;
    private String username;
    private Long deptId;
    private String phone;
    private Long jobId;
    private Date createTime;
    private Date lastPasswordResetTime;
    private String nickName;
    private String sex;
}

package com.yiyuan.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("user_avatar")//表名
public class UserAvatar {

    private Long id;
    private String realName;
    private String path;
    private String size;
    private Date createTime;

}

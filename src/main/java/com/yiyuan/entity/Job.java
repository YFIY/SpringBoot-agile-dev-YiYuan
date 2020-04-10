package com.yiyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@TableName("job")//表名
public class Job implements Serializable {

    private Long id;


    private String name;

    private Long sort;


    private Boolean enabled;

    private Dept dept;

    private Timestamp createTime;


}

package com.yiyuan.entity.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//表模型
@Data
@TableName("dept")//表名
public class DeptSqlEntity {

    private Long id;
    private String name;
    private Long pid;
    private Long enabled;
    private Date createTime;

}

package com.yiyuan.query;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
public class UserQueryCriteria implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 部门ID集合
     */
    private Set<Long> deptIds;

    /**
     * 模糊查询【email,username,nickName】
     */
    private String blurry;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 创建时间在两个时间段之间的
     */
    private List<Timestamp> createTime;
}


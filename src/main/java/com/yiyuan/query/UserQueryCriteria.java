package com.yiyuan.query;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
public class UserQueryCriteria implements Serializable {

    private Long id;

    private Set<Long> deptIds;

    private String blurry;

    private Boolean enabled;

    private Long deptId;

    private List<Timestamp> createTime;
}


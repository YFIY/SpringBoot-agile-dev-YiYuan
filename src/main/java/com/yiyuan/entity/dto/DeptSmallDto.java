package com.yiyuan.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeptSmallDto implements Serializable {

    // ID
    private Long id;

    // 名称
    private String name;
}

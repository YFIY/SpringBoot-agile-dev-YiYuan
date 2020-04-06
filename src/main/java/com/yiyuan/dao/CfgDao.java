package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.CfgEntity;

/**
 * 系统参数业务数据访问接口
 * @author MoLi
 */
public interface CfgDao extends BaseMapper<CfgEntity> {

    /**
     * 根据参数名查一条数据
     */
    CfgEntity findByCfgName(String cfgName);

}

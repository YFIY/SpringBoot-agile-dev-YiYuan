package com.yiyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.dao.CargoBillDao;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.sql.CargoBillSqlEntity;

/**
 * 货运单功能业务接口
 * @author MoLi
 */
public interface CargoBillService extends IService<CargoBillSqlEntity> {

    /**
     * 动态条件分页查询
     */
    public IPage<CargoBillSqlEntity> getListMapPage(IPage<CargoBillSqlEntity> page, CargoBillSqlEntity queryModel);

}

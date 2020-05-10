package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.CargoBillDao;
import com.yiyuan.dao.CfgDao;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.sql.CargoBillSqlEntity;
import com.yiyuan.entity.vo.CargoBillVoEntity;
import com.yiyuan.service.CargoBillService;
import com.yiyuan.service.CfgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CargoBillServiceImpl extends ServiceImpl<CargoBillDao, CargoBillSqlEntity> implements CargoBillService {

    /**
     * 动态条件分页查询
     */
    public IPage<CargoBillVoEntity> getListMapPage(IPage<CargoBillVoEntity> page, CargoBillSqlEntity queryModel){
        return this.baseMapper.getListMapPage(page,queryModel);
    }

}

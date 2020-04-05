package com.yiyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.UserInfoEntity;

/**
 * @Description 系统参数业务接口
 *              注意:不要直接调用该类获取配置参数,请注入[ConfigCache]接口使用
 * @Author MoLi
 */
public interface CfgService extends IService<CfgEntity> {

    //根据参数名查一条数据
    CfgEntity findByCfgName(String cfgName);

}

package com.yiyuan.cache.impl;

import com.yiyuan.cache.CacheDao;
import com.yiyuan.cache.ConfigCache;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.service.CfgService;
import com.yiyuan.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 全局系统配置缓存实现类
 * 注意:不要直接调用该类获取配置参数,请注入[ConfigCache]接口使用
 *
 * @author MoLi
 */
@Service
public class ConfigCacheImpl implements ConfigCache {
    private static  final Logger logger = LoggerFactory.getLogger(ConfigCacheImpl.class);
    @Autowired
    private CacheDao cacheDao;
    @Autowired
    private CfgService cfgService;


    /**
     * 通过参数名获取参数值
     * 注意:使用此方法只会从本地缓存中取,如果缓存中没有则返回NULL
     * @Author MoLi
     * @Param  key 参数名
     * @Return 参数值
     */
    @Override
    public String get(String key) {
        return (String) cacheDao.hget(CacheDao.CONSTANT,key);
    }

    /**
     * 自定义数据源,通过参数名获取参数值
     * @Author MoLi
     * @Param  key 参数名
     * @Param  local 布尔含义:[true]从本地缓存中获取 [false]从数据库中获取
     * @Return 参数值
     */
    @Override
    public String get(String key, boolean local) {
        String ret = null;
        if(local) {
             ret = get(key);
        }else{
            ret = cfgService.findByCfgName(key).getCfgValue();
            set(key,ret);
        }
        return ret;
    }

    @Override
    public String get(String key, String def) {
        String ret =  get(key);
        if(StringUtil.isEmpty(ret)){
            return ret;
        }
        return ret;
    }


    @Override
    public void set(String key, Object val) {
        cacheDao.hset(CacheDao.CONSTANT,key,val);
    }

    @Override
    public void del(String key, String val) {
        cacheDao.hdel(CacheDao.CONSTANT,val);
    }

    @Override
    public void cache() {
        logger.info("reset config cache");
        List<CfgEntity> list = cfgService.list();
        if (list != null && !list.isEmpty()) {
            for (CfgEntity cfg : list) {
                if (StringUtil.isNotEmpty(cfg.getCfgName()) && StringUtil.isNotEmpty(cfg.getCfgValue())) {
                    set(cfg.getCfgName(),cfg.getCfgValue());
                }
            }
        }
    }
}

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
    private static final Logger logger = LoggerFactory.getLogger(ConfigCacheImpl.class);
    @Autowired
    private CacheDao cacheDao;
    @Autowired
    private CfgService cfgService;


    /**
     * 通过参数名获取参数值
     * [说明]:此方法首先从缓存中获取参数,如果获取不到则从数据库获取并存入缓存
     *
     * @param key 参数名
     * @return 参数值
     * @author MoLi
     */
    @Override
    public String get(String key) {

        //从本地缓存获取
        String ret = (String) cacheDao.hget(CacheDao.CONSTANT, key);

        //如果本地缓存中没有数据
        if (StringUtil.isEmpty(ret)) {
            //从数据库获取
            ret = cfgService.findByCfgName(key).getCfgValue();
            //如果数据库中有数据
            if(!StringUtil.isEmpty(ret)){
                //数据放入缓存
                set(key, ret);
            }
        }

        return ret;
    }

    /**
     * 自定义数据源,通过参数名获取参数值
     *
     * @param key   参数名
     * @param local 布尔含义:[true]从本地缓存中获取 [false]从数据库中获取
     * @return 参数值
     * @author MoLi
     */
    @Override
    public String get(String key, boolean local) {
        String ret = null;
        if (local) {
            //从本地缓存获取
            ret = (String) cacheDao.hget(CacheDao.CONSTANT, key);
        } else {
            //从数据库获取
            ret = cfgService.findByCfgName(key).getCfgValue();
            //数据放入缓存
            set(key, ret);
        }
        return ret;
    }

    /**
     * 通过参数名获取参数值(带默认值)
     * [说明]:如果get方法获取不到数据,则使用def的值(默认参数)
     *
     * @param key   参数名
     * @param def 默认参数值
     * @return 参数值
     * @author MoLi
     */
    @Override
    public String get(String key, String def) {
        String ret = get(key);
        if (StringUtil.isEmpty(ret)) {
            return def;
        }
        return ret;
    }


    /**
     * 设置缓存数据
     *
     * @param key 参数名(key)
     * @param val 参数值(value)
     */
    @Override
    public void set(String key, Object val) {
        cacheDao.hset(CacheDao.CONSTANT, key, val);
    }


    /**
     * 删除配置指定名字的缓存
     * @param type 缓存类型标识
     * @param val 参数名
     */
    @Override
    public void del(String type, String val) {
        cacheDao.hdel(CacheDao.CONSTANT, val);
    }

    /**
     * 删除配置指定名字的缓存
     * @param val 参数名
     */
    public void del(String val) {
        cacheDao.hdel(CacheDao.CONSTANT, val);
    }

    /**
     * 将数据库中的数据加载到缓存中
     */
    @Override
    public void cache() {
        logger.info("开始重新将全局配置从数据库加载进缓存");
        //从数据库获取全部的全局配置数据
        List<CfgEntity> list = cfgService.list();
        //如果集合不为空
        if (list != null && !list.isEmpty()) {
            //遍历数据
            for (CfgEntity cfg : list) {
                //如果数据不为空
                if (StringUtil.isNotEmpty(cfg.getCfgName()) && StringUtil.isNotEmpty(cfg.getCfgValue())) {
                    //将数据放入缓存
                    set(cfg.getCfgName(), cfg.getCfgValue());
                }
            }
        }
    }
}

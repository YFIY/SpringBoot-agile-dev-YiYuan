package com.yiyuan.cache.impl;

import com.yiyuan.cache.CacheDao;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Ehcache缓存实现类<br>
 *     请不要直接使用该类，而是使用其接口CacheDao，以方便实际应用中往其他缓存服务切换（比如redis,ssdb等)
 *
 * @author MoLi
 */
@Component
public class EhcacheDao implements CacheDao {
    @Resource
    private CacheManager cacheManager;

    /**
     * 将数据放入缓存
     * 设置hash key值
     *
     * @param key 缓存类型
     * @param k 缓存名
     * @param val 缓存值
     */
    @Override
    public void hset(Serializable key, Serializable k, Object val) {
        Cache cache = cacheManager.getCache(String.valueOf(key));
        cache.put(k,val);
    }

    /**
     * 将数据放入缓存(默认为全局配置的缓存)
     * 设置hash key值
     *
     * @param key 缓存名
     * @param val 缓存值
     */
    @Override
    public void set(Serializable key, Object val) {
        Cache cache = cacheManager.getCache(CONSTANT);
        cache.put(key,val);

    }

    /**
     * 从缓存中取指定名字的String类型数据
     * 获取hash key值
     *
     * @param key 缓存类型
     * @param k 缓存名
     * @return 缓存值(String类型)
     */
    @Override
    public Serializable hget(Serializable key, Serializable k) {
        Cache cache = cacheManager.getCache(String.valueOf(key));
        return cache.get(k, String.class);

    }

    /**
     * 从缓存中取指定名字的指定类型数据
     * 获取hash key值
     *
     * @param key 缓存类型
     * @param k 缓存名
     * @param klass 缓存值的类型
     * @return 缓存值(klass指定类型)
     */
    @Override
    public <T>T hget(Serializable key, Serializable k, Class<T> klass) {
        Cache cache = cacheManager.getCache(String.valueOf(key));
        return cache.get(k,klass);
    }


    /**
     * 从缓存中取指定名字的指定类型数据(默认为全局配置的缓存)
     * 获取hash key值
     *
     * @param key 缓存名
     * @param klass 缓存值的类型
     * @return 缓存值(klass指定类型)
     */
    @Override
    public <T>T get(Serializable key, Class<T> klass) {
        return cacheManager.getCache(CONSTANT).get(String.valueOf(key),klass);
    }

    /**
     * 从缓存中取指定名字的String类型的数据(默认为全局配置的缓存)
     * 获取hash key值
     *
     * @param key 缓存名
     * @return 缓存值(String类型)
     */
    @Override
    public String get(Serializable key) {
        return cacheManager.getCache(CONSTANT).get(String.valueOf(key), String.class);
    }

    /**
     * 删除指定缓存名的缓存数据(默认为全局配置的缓存)
     * 获取hash key值
     *
     * @param key 缓存名
     */
    @Override
    public void del(Serializable key) {
        cacheManager.getCache(CONSTANT).put(String.valueOf(key),null);
    }

    /**
     * 删除指定缓存类型以及指定名字的缓存
     *
     * @param key 类型标识
     * @param k 缓存名
     */
    @Override
    public void hdel(Serializable key, Serializable k) {
        cacheManager.getCache(String.valueOf(key)).put(String.valueOf(k),null);
    }
}

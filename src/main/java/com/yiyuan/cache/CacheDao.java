package com.yiyuan.cache;

import java.io.Serializable;

/**
 * 缓存数据接口
 *
 * @author MoLi
 */
public interface CacheDao {

    /**
     * 全局配置标识
     */
    String CONSTANT = "CONSTANT";
    /**
     * Session标识
     */
    String SESSION = "SESSION";

    /**
     * 将数据放入缓存
     * 设置hash key值
     *
     * @param key 缓存类型
     * @param k 缓存名
     * @param val 缓存值
     */
    void hset(Serializable key, Serializable k, Object val);

    /**
     * 将数据放入缓存(默认为指定类型的缓存)
     * 设置hash key值
     *
     * @param key 缓存名
     * @param val 缓存值
     */
    void set(Serializable key, Object val);

    /**
     * 从缓存中取指定名字的String类型数据
     * 获取hash key值
     *
     * @param key 缓存类型
     * @param k 缓存名
     * @return 缓存值(String类型)
     */
    Serializable hget(Serializable key, Serializable k);

    /**
     * 从缓存中取指定名字的指定类型数据
     * 获取hash key值
     *
     * @param key 缓存类型
     * @param k 缓存名
     * @param klass 缓存值的类型
     * @return 缓存值(klass指定类型)
     */
    <T> T hget(Serializable key, Serializable k, Class<T> klass);


    /**
     * 从缓存中取指定名字的指定类型数据(带默认缓存类型)
     * 获取hash key值
     *
     * @param key 缓存名
     * @param klass 缓存值的类型
     * @return 缓存值(klass指定类型)
     */
    <T> T get(Serializable key, Class<T> klass);


    /**
     * 从缓存中取指定名字的String类型的数据(带默认缓存类型)
     * 获取hash key值
     *
     * @param key 缓存名
     * @return 缓存值(String类型)
     */
    String get(Serializable key);

    /**
     * 删除指定缓存名的缓存数据(带默认缓存类型)
     * 获取hash key值
     *
     * @param key 缓存名
     */
    void del(Serializable key);

    /**
     * 删除指定缓存类型以及指定名字的缓存
     *
     * @param key 类型标识
     * @param k 缓存名
     */
    void hdel(Serializable key, Serializable k);
}

package com.yiyuan.cache;

/**
 * 顶级缓存接口
 */
public interface Cache {
	/**
	 * 将数据库中的数据加载到缓存中
	 */
	void cache();


	/**
	 * 获取缓存数据
	 *
	 * @param key key
	 * @return value
	 */
	String get(String key);


	/**
	 * 设置缓存数据
	 *
	 * @param key 缓存的名字(key)
	 * @param val 缓存的内容(value)
	 */
	void set(String key, Object val);


}

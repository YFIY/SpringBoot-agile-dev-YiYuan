package com.yiyuan.cache;

/**
 * 全局系统配置数据访问
 */
public interface ConfigCache extends Cache {


	/**
	 * 获取全局配置参数值，可选从本地缓存或数据库获取数据
	 * @param key
	 * @return
	 */
	String get(String key, boolean local);

	/**
	 * 获取全局配置参数值(带默认值)
	 *
	 * @param key the key
	 * @param def the default value
	 * @return the config
	 */
	String get(String key, String def);

	/**
	 * 删除缓存
	 * @param key
	 * @param val
	 */
	void del(String key, String val);
}

package com.yiyuan.cache;

/**
 * 全局系统配置数据访问
 * @author MoLi
 */
public interface ConfigCache extends Cache {


	/**
	 * 自定义数据源,通过参数名获取参数值
	 *
	 * @param key   参数名
	 * @param local 布尔含义:[true]从本地缓存中获取 [false]从数据库中获取
	 * @return 参数值
	 * @author MoLi
	 */
	String get(String key, boolean local);

	/**
	 * 通过参数名获取参数值(带默认值)
	 * [说明]:如果get方法获取不到数据,则使用def的值(默认参数)
	 *
	 * @param key   参数名
	 * @param def 默认参数值
	 * @return 参数值
	 * @author MoLi
	 */
	String get(String key, String def);

	/**
	 * 删除缓存
	 * @param key 缓存类型标识
	 * @param val 缓存名
	 */
	void del(String key, String val);
}

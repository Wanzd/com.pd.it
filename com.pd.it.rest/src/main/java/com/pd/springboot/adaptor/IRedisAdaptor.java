package com.pd.springboot.adaptor;

/**
 * redis适配器
 * 
 * @author thinkpad
 *
 */
public interface IRedisAdaptor {
	/**
	 * 简单查询：通过key查询value
	 * 
	 * @param key
	 *            String
	 * @return String
	 */
	String query(String key);

	/**
	 * 简单保存：通过key保存value
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	void set(String key, String value);

	/**
	 * 简单删除：通过key删除
	 * 
	 * @param key
	 *            String
	 */
	void delete(String key);
}

package com.soulagou.commons.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseRedisDAO<T, ID extends Serializable> {
	
	/**
	 * 获取单个对象
	 * @param id
	 * @return
	 */
	T find(ID id);

	/**
	 * 保存单个对象
	 * @param id
	 * @param object
	 */
	void save(ID id, T object);
	
	/**
	 * 删除单个对象
	 * @param id
	 */
	void delete(ID id);
	
	/**
	 * 获取列表
	 * @param arg0
	 * @return
	 */
	List<T> getList(Collection<ID> arg0);
	
	/**
	 * 列表操作压入队列
	 * @param id
	 * @param object
	 */
	void push(ID id, T object);
	
	/**
	 * 列表操作范围取值
	 * @param id
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	List<T> range(ID id, int firstResult, int maxResults);
	
	/**
	 * 列表操作求count
	 * @param id
	 * @return
	 */
	long count(ID id);
	
	/**
	 * 判断对象是否存在
	 * @param id
	 * @return
	 */
	boolean hasKey(ID id);

}
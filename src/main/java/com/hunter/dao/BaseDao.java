package com.hunter.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * 保存或更新
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查询集合带分页
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几页
	 * @param rows
	 *            页大小
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 查询集合带分页
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows);

	/**
	 * 获取一个对象
	 * 
	 * @param c
	 * @param id
	 * @return
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 获取一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, Object[] param);

	/**
	 * 获取一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	public Long count(String hql);

	public BigInteger sqlCount(String hql);

	public Long count(String hql, List<Object> param);

	public Long count(String hql, Object[] param);

	public Integer executeHql(String hql);

	public Integer executeHql(String hql, Object[] param);

	public Integer executeHql(String hql, List<Object> param);

	/**
	 * 批量增加
	 * 
	 * @param lists
	 * @return
	 */
	Integer saveBatch(List<T> lists);

}

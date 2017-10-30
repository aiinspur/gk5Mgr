package com.tigerj.service.common;

import java.util.List;

import com.tigerj.mybatis.Page;

public interface CrudService<T> {

	/**
	 * 获取单条数据
	 * 
	 */
	public T selectById(Long id);

	/**
	 * 查询列表数据
	 * 
	 */
	public List<T> findList(T entity);
	
	
	public int insert(T entity);

	/**
	 * 查询分页数据
	 * 
	 * @param page
	 *            分页对象
	 * @param entity entity
	 * @return return
	 */
	public Page<T> findPage(Page<T> page, T entity);

	public void saveOrUpdate(T entity);

	public void delete(T entity) ;

}

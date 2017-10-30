package com.tigerj.dao.common;

import java.util.List;


public interface CrudDao<T> extends BaseDao {

	
	public T selectById(Long id);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page());  
	 */
	public List<T> findList(T entity);

	/**
	 * 查询所有数据列表
	 */
	public List<T> findAllList(T entity);

	/** 
	 *查询所有数据列表  
	 */
	@Deprecated
	public List<T> findAllList();

	/** 
	 * 插入数据
	 */
	public int insert(T entity);

	/** 
	 * 更新数据  
	 */
	public int update(T entity);

	/** 
	 * 删除数据 
	 */
	@Deprecated
	public int delete(String id);

	/** 
	 * 删除数据
	 */
	public int delete(T entity);

}
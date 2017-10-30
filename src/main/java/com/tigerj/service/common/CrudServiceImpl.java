package com.tigerj.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tigerj.dao.common.CrudDao;
import com.tigerj.dao.common.DataEntity;
import com.tigerj.mybatis.Page;


@Transactional(readOnly = true)
public abstract class CrudServiceImpl<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 获取单条数据
	 * 
	 * @param id i
	 * @return dao.get(id);
	 */
	public T selectById(Long id) {
		return dao.selectById(id);
	}

	
	@Transactional(readOnly = false)
	public int insert(T entity) {
		return dao.insert(entity);
	}

	/**
	 * 查询列表数据
	 * 
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	/**
	 * 查询分页数据
	 * 
	 * @param page
	 *            分页对象
	 * @param entity entity
	 * @return return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity entity
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(T entity) {
		if (entity.getId() == null) {
			entity.preInsert(entity);
			dao.insert(entity);
		} else {
			entity.preUpdate();
			dao.update(entity);
		}
	}

	
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

}

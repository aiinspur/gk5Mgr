package com.tigerj.dao.common;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.tigerj.mybatis.Page;

import lombok.Data;

@Data
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected Long id;

	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;

	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;

	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert(T t);

	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity<?> that = (BaseEntity<?>) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}


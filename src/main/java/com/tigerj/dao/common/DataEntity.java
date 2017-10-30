package com.tigerj.dao.common;

import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tigerj.utils.IdGen;

import lombok.Data;



@Data
@JsonIgnoreProperties({"page","sqlMap"})
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	
	protected String remarks;	// 备注
	protected Date createDate;	// 创建日期
	protected Date updateDate;	// 更新日期
	protected Status status; 	// 记录状态（0：正常；1：删除；2：审核）
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(T t) {
		Table table = t.getClass().getAnnotation(Table.class);
		Long id = IdGen.querySeq(table.name());
		setId(id);		
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {
		this.updateDate = new Date();
	}
	
	

}

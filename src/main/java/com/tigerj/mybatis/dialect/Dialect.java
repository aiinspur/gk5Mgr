package com.tigerj.mybatis.dialect;

/**
 * 类似HIBERNATE的Dialect,但只精简出分页部分
 *
 * @author hu.jiang
 * @version 1.0
 */
public interface Dialect {

	/**
	 * 数据库本身是否支持当前的分页查询方式 
	 * 如果数据库不支持的话，则不进行数据库分页
	 *
	 * @return true：支持当前的分页查询方式
	 */
	public boolean supportsLimit();

	/**
	 * 将SQL转换为分页SQL，分别调用分页SQL
	 *
	 * @param sql
	 *            SQL语句
	 * @param offset
	 *            开始条数
	 * @param limit
	 *            每页显示多少纪录条数
	 * @return 分页查询的SQL
	 */
	public String getLimitString(String sql, int offset, int limit);

}

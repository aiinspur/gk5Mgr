
package com.tigerj.utils;

import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 封装各种生成唯一性ID算法的工具类.
 */
@Component
@Lazy(false)
public class IdGen {

	private static SecureRandom random = new SecureRandom();

	public static synchronized long querySeq(String tableName) {
		tableName = getSeqTableNameFromTablName(tableName);
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		jdbcTemplate.update("REPLACE INTO " + tableName + "(stub) VALUES ('a')");
		return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
	}

	private static String getSeqTableNameFromTablName(String tableName) {
		if (StringUtils.isEmpty(tableName)) {
			return "";
		}
		return tableName.toUpperCase().replaceFirst("T_", "T_SEQ_");

	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 * @return String
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long. 
	 * @return long
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	private static JdbcTemplate getJdbcTemplate() {
		return SpringContextHolder.getBean(JdbcTemplate.class);
	}
}

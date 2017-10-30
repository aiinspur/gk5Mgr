package com.tigerj.idGenTest;

import java.util.concurrent.CountDownLatch;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tigerj.utils.IdGen;

public class CustomerSaveTask extends Thread {

	CountDownLatch countDownLatch;

	IdGen idGen;

	JdbcTemplate jdbcTemplate;

	public CustomerSaveTask(CountDownLatch countDownLatch, IdGen idGen, JdbcTemplate jdbcTemplate) {
		this.countDownLatch = countDownLatch;
		this.idGen = idGen;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run() {
		long id = idGen.querySeq("T_TEST_CUSTOMER");
		System.out.println("CUSTOMER ID:" + id);
		String sql = "insert into t_test_customer(id,first_name,last_name)  values(?,?,?)";
		jdbcTemplate.update(sql, new Object[] { id, "jiang", "hu" });
		countDownLatch.countDown();
	}

}

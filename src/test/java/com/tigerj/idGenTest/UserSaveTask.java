package com.tigerj.idGenTest;

import java.util.concurrent.CountDownLatch;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tigerj.utils.IdGen;

public class UserSaveTask extends Thread{
	
	CountDownLatch countDownLatch;
	
	IdGen idGen;
	
	JdbcTemplate jdbcTemplate;
	
	public UserSaveTask(CountDownLatch countDownLatch,IdGen idGen,JdbcTemplate jdbcTemplate) {
		this.countDownLatch = countDownLatch;
		this.idGen = idGen;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run() {
		long id = idGen.querySeq("T_TEST_USER");
		System.out.println("user id:"+id);
		String sql = "insert into t_test_user(id)  values(?)";  
	    jdbcTemplate.update(sql,new Object[] {id});  
	    countDownLatch.countDown();
	}
}

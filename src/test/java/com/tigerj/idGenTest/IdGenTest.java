package com.tigerj.idGenTest;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.tigerj.utils.IdGen;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGenTest {
	
	@Autowired
	IdGen idGen;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	public void querySeqTest() {
		// System.out.println(idGen.querySeq("T_TEST_USER"));
	}
	
	@Test
	public void seqRepeatTest(){
		CountDownLatch countDownLatch = new CountDownLatch(10);
		UserSaveTask userSaveTask1 = new UserSaveTask(countDownLatch, idGen, jdbcTemplate);
		userSaveTask1.start();
		UserSaveTask userSaveTask2 = new UserSaveTask(countDownLatch, idGen, jdbcTemplate);
		userSaveTask2.start();
		UserSaveTask userSaveTask3 = new UserSaveTask(countDownLatch, idGen, jdbcTemplate);
		userSaveTask3.start();
		UserSaveTask userSaveTask4 = new UserSaveTask(countDownLatch, idGen, jdbcTemplate);
		userSaveTask4.start();
		UserSaveTask userSaveTask5 = new UserSaveTask(countDownLatch, idGen, jdbcTemplate);
		userSaveTask5.start();
		
		CustomerSaveTask customerSaveTask1 = new CustomerSaveTask(countDownLatch, idGen, jdbcTemplate);
		customerSaveTask1.start();
		CustomerSaveTask customerSaveTask2 = new CustomerSaveTask(countDownLatch, idGen, jdbcTemplate);
		customerSaveTask2.start();
		CustomerSaveTask customerSaveTask3 = new CustomerSaveTask(countDownLatch, idGen, jdbcTemplate);
		customerSaveTask3.start();
		CustomerSaveTask customerSaveTask4 = new CustomerSaveTask(countDownLatch, idGen, jdbcTemplate);
		customerSaveTask4.start();
		CustomerSaveTask customerSaveTask5 = new CustomerSaveTask(countDownLatch, idGen, jdbcTemplate);
		customerSaveTask5.start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

}


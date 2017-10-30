package com.tigerj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerj.dao.common.Status;
import com.tigerj.domain.TestCustomer;
import com.tigerj.service.CustomerService;

public class CustomerRepositoryTest extends BaseTest{
	
	@Autowired
	CustomerService customerService;
	
	@Test
	public void saveTest(){
		TestCustomer entity = new TestCustomer();
		//entity.setStatus(Status.NORMAL);
		entity.setFirstName("Jiang");
		entity.setLastName("hu1");
		customerService.saveOrUpdate(entity);
	}
	
	@Test
	public void updateTest(){
		
		TestCustomer result = customerService.selectById(123L);
		assertNotNull(result);
		
		TestCustomer entity = new TestCustomer();
		entity.setId(result.getId());
		entity.setStatus(Status.NORMAL);
		entity.setFirstName("Wei");
		entity.setLastName("qiang");
		customerService.saveOrUpdate(entity);
		
		TestCustomer customer = customerService.selectById(123L);
		assertEquals(customer.getFirstName(), "Wei");
	}
	

}

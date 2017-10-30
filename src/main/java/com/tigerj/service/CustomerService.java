package com.tigerj.service;

import java.util.List;

import com.tigerj.domain.TestCustomer;
import com.tigerj.service.common.CrudService;

public interface CustomerService extends CrudService<TestCustomer>{
	
	public List<TestCustomer> selectByLastName(String lastName);
	
	public List<TestCustomer> selectByFirstName(String firstName);
	
	public boolean isExist(Long id);

}

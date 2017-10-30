package com.tigerj.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerj.dao.TestCustomerMapper;
import com.tigerj.domain.TestCustomer;
import com.tigerj.service.CustomerService;
import com.tigerj.service.common.CrudServiceImpl;


@Service
public class CustomerServiceImpl<T> extends CrudServiceImpl<TestCustomerMapper, TestCustomer> implements CustomerService {
	
	@Autowired
	TestCustomerMapper customerDao;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	HttpServletResponse httpServletResponse;
	
	@Override
	public List<TestCustomer> selectByLastName(String lastName) {
		
		return customerDao.selectByLastName(lastName);
	}

	@Override
	public List<TestCustomer> selectByFirstName(String firstName) {
		return customerDao.findByFirstName(firstName);
	}

	@Override
	public boolean isExist(Long id) {
		if (id != null && selectById(id) != null) {
			return true;
		}
		return false;
	}
	
	
	

}

package com.tigerj.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerj.dao.TestCityMapper;
import com.tigerj.domain.TestCity;
import com.tigerj.service.TestCityService;
import com.tigerj.service.common.CrudServiceImpl;


@Service
public class TestCityServiceImpl<T> extends CrudServiceImpl<TestCityMapper, TestCity> implements TestCityService {
	
	@Autowired
	TestCityMapper TestCityDao;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	HttpServletResponse httpServletResponse;
	

}

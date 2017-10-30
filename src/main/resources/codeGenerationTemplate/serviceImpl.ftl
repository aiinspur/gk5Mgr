package com.tigerj.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerj.dao.${className}Mapper;
import com.tigerj.domain.${entityName};
import com.tigerj.service.${className}Service;
import com.tigerj.service.common.CrudServiceImpl;


@Service
public class ${className}ServiceImpl<T> extends CrudServiceImpl<${className}Mapper, ${entityName}> implements ${className}Service {
	
	@Autowired
	${className}Mapper ${className}Dao;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	HttpServletResponse httpServletResponse;
	

}

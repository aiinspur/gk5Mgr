package com.tigerj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.tigerj.dao.common.CrudDao;
import com.tigerj.domain.TestCity;

@Mapper
public interface TestCityMapper extends CrudDao<TestCity>{
	
}

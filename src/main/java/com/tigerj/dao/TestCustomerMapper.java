package com.tigerj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.tigerj.dao.common.CrudDao;
import com.tigerj.domain.TestCustomer;

@Mapper
public interface TestCustomerMapper extends CrudDao<TestCustomer>{
	
	List<TestCustomer> selectByLastName(String lastName);

	@Select("select * from t_test_customer where first_name = #{firstName}")
	@Results({
        @Result(property = "firstName",  column = "first_name", javaType = String.class),
        @Result(property = "lastName", column = "last_name")
    })
	List<TestCustomer> findByFirstName(@Param("firstName") String firstName);
	
	

}

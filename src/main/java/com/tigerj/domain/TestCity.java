package com.tigerj.domain;

import javax.persistence.Table;

import com.tigerj.dao.common.DataEntity;

import lombok.Data;

@Table(name="T_TEST_USER")
@Data
public class TestCity extends DataEntity<TestCity> {
	
	private static final long serialVersionUID = 1L;
    
    private String name;  
    private String loginTest;  
    private String uuuuYyyyOoo;  
}

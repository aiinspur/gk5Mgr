package com.tigerj.domain;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tigerj.dao.common.DataEntity;

import lombok.Data;

@Table(name="T_TEST_CUSTOMER")
@Data
public class TestCustomer extends DataEntity<TestCustomer> {
	
	private static final long serialVersionUID = 1L;

	private String firstName;
    
    private String lastName;
    
}

package com.tigerj.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tigerj.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	List<Customer> findByLastName(String lastName);

}

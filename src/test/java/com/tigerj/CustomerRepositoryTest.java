package com.tigerj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tigerj.domain.Customer;
import com.tigerj.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void jpaTest(){
		customerRepository.save(new Customer("Jiang", "Hu"));
		customerRepository.save(new Customer("Li", "Hu"));
		customerRepository.save(new Customer("Liu", "XianWei"));
		customerRepository.save(new Customer("Hou", "Cong"));
		
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer.toString());
		}
		System.out.println();
		
		Customer customer = customerRepository.findOne(1L);
		System.out.println("Customer found with findOne(1L):");
		System.out.println(customer.toString());
		System.out.println();
		
		System.out.println("Customer found with findByLastName('Hu'):");
		for (Customer hu : customerRepository.findByLastName("Hu")) {
			System.out.println(hu.toString());
		}
		
	}

}

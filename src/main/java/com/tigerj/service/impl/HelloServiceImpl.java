package com.tigerj.service.impl;


import org.springframework.stereotype.Service;

import com.tigerj.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public void sayHello() {
		System.out.println("Hello Tigerj.");
		
	}

}

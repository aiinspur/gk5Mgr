/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tigerj.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tigerj.domain.TestCustomer;
import com.tigerj.mybatis.Page;
import com.tigerj.service.CustomerService;
import com.tigerj.web.common.BaseController;
import com.tigerj.web.common.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api("customer rest api.")
@RestController
@RequestMapping("api/customer")
public class TestCustomerController extends BaseController{
	
	@Autowired
	CustomerService customerService;
	
	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	
	@GetMapping("index")
	public String index(Map<String, Object> model) {
		model.put("message", this.message);
		return "index";
	}
	
	@GetMapping
	public List<TestCustomer> getCustomers(TestCustomer customer) {
		return customerService.findList(customer);
	}
	
	@ApiOperation(value="获取用户详细信息", notes="根据id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@GetMapping("{id}")
	public ResponseEntity<ResultVo<TestCustomer>> getCustomerById(@PathVariable("id") Long id) {
		TestCustomer customer = customerService.selectById(id);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ResultVo<>(customer),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> addCustomer(@RequestBody TestCustomer customer,UriComponentsBuilder uriComponentsBuilder) {
		if (customerService.isExist(customer.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		customerService.saveOrUpdate(customer);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TestCustomer> updateCustomerById(@PathVariable("id") Long id,@RequestBody TestCustomer customer) {
		TestCustomer curCustomer = customerService.selectById(id);
		if (curCustomer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		curCustomer.setFirstName(customer.getFirstName());
		curCustomer.setLastName(customer.getLastName());
		customerService.saveOrUpdate(curCustomer);
		return new ResponseEntity<>(curCustomer,HttpStatus.OK);
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<TestCustomer> deleteCustomer(@PathVariable("id") Long id) {
		TestCustomer customer = customerService.selectById(id);
		
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("page")
	public Page<TestCustomer> pageTest(TestCustomer customer,HttpServletRequest request,HttpServletResponse response) {
		return customerService.findPage(new Page<>(request, response), customer);
	}
	
	@GetMapping("yingxiang")
	public String yingXiangUpload(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "uploadFile";
	}
	
	@GetMapping("/restGet")
	public ResponseEntity<String> restGet(){
		return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
	}

	@GetMapping("/fail")
	public String fail() {
		throw new Gk5Exception("Oh dear!");
	}

	@GetMapping("/fail2")
	public String fail2() {
		throw new IllegalStateException();
	}


}

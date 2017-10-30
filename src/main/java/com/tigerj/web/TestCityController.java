
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

import com.tigerj.domain.TestCity;
import com.tigerj.mybatis.Page;
import com.tigerj.service.TestCityService;
import com.tigerj.web.common.BaseController;
import com.tigerj.web.common.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api("TestCity rest api.")
@RestController
@RequestMapping("api/TestCity")
public class TestCityController extends BaseController{

	@Autowired
	TestCityService testCityService;

	
	@ApiOperation(value="query TestCity ", notes="query TestCity by id")
	@ApiImplicitParam(name = "id", value = "TestCity ID", required = true, dataType = "Long", paramType = "path")
	@GetMapping("{id}")
	public ResponseEntity<ResultVo<TestCity>> getTestCityById(@PathVariable("id") Long id) {
		TestCity testCity = testCityService.selectById(id);
		if (testCity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ResultVo<>(testCity),HttpStatus.OK);
	}


}

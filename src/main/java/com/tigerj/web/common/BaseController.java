package com.tigerj.web.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerj.web.Gk5Exception;

public abstract class BaseController {
	
	@ExceptionHandler(Gk5Exception.class)
	public @ResponseBody ResponseEntity<Object> handleRuntimeException(Gk5Exception exception) {
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

}

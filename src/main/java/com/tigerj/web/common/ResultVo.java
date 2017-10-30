package com.tigerj.web.common;

import com.tigerj.constant.StatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangweiqiang on 2017/10/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> {
	private Integer code;
	private String msg;
	private T data;

	public ResultVo(StatusCode sc) {
		code = sc.getCode();
		msg = sc.getDesc();
	}

	public ResultVo(StatusCode sc, T data) {
		code = sc.getCode();
		msg = sc.getDesc();
		this.data = data;
	}
	
	public ResultVo(T data) {
		code = StatusCode.SUCCESS.getCode();
		msg = StatusCode.fromCode(code).getDesc();
		this.data = data;
	}

	public static <T> ResultVo<T> error() {
		return new ResultVo<>(StatusCode.SYSTEM_ERR);
	}

	public static <T> ResultVo<T> error(StatusCode statusCode) {
		return new ResultVo<>(statusCode);
	}

	public static <T> ResultVo<T> success() {
		return new ResultVo<>(StatusCode.SUCCESS);
	}

	public static <T> ResultVo<T> success(T data) {
		return new ResultVo<>(StatusCode.SUCCESS, data);
	}
}
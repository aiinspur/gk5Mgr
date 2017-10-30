package com.tigerj.dao.common;

public enum Status {

	NORMAL(0), DELETED(1), AUDIT(2);

	private int statusCode;

	private Status(int statusCode) {
		this.statusCode = statusCode;
	}

}

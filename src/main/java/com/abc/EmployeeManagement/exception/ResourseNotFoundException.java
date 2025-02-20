package com.abc.EmployeeManagement.exception;

import org.springframework.http.HttpStatus;

public class ResourseNotFoundException extends RuntimeException {
    private final HttpStatus status;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourseNotFoundException(HttpStatus status,String message) {
		super(message);
		this.status=status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

}

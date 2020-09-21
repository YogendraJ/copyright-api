package com.deloitte.copyright.model;

import java.util.List;

/**
 * Pojo class which is used by GlobalExceptionHandler class to 
 * return custom Exception Response in JSON format
 * @author Yogendra Joshi
 */
public class ExceptionResponse {

	public ExceptionResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	private List<String> details;

}
package com.exchange.api.dto;

public class ErrorResponse {
	
	private String message;
	
	public ErrorResponse(String message) {
		this.message = message;
	}
	
	protected ErrorResponse() {}
	
	public String getMessage() {
		return message;
	}

}

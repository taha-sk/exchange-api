package com.exchange.api.exception;

public class InvalidRequestException extends RuntimeException {
	
	private static final long serialVersionUID = -795928496907693671L;

	public InvalidRequestException(String message) {
		super(message);
	}

}

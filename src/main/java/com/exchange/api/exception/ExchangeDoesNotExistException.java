package com.exchange.api.exception;

public class ExchangeDoesNotExistException extends RuntimeException {
	
	private static final long serialVersionUID = -795928496907693671L;

	public ExchangeDoesNotExistException(long id) {
		super("Exchange does not exist with id: " + id);
	}

}

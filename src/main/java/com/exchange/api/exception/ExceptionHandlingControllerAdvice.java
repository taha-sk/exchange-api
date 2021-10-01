package com.exchange.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exchange.api.dto.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ErrorResponse invalidRequestExceptionHandler(InvalidRequestException ex) {
		return new ErrorResponse(ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(ExchangeDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ErrorResponse exchangeDoesNotExistExceptionHandler(ExchangeDoesNotExistException ex) {
		return new ErrorResponse(ex.getMessage());
	}

}

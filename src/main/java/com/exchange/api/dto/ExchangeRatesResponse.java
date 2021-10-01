package com.exchange.api.dto;

public class ExchangeRatesResponse {
	
	private Double rate;
	
	public ExchangeRatesResponse(Double rate) {
		this.rate = rate;
	}
	
	protected ExchangeRatesResponse() {}
	
	public Double getRate() {
		return rate;
	}
	
}

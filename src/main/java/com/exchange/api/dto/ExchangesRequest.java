package com.exchange.api.dto;

public class ExchangesRequest {
	
	private Double sourceAmount;
	private String sourceCurrency;
	private String targetCurrency;
	
	public ExchangesRequest(Double sourceAmount, String sourceCurrency, String targetCurrency) {
		this.sourceAmount = sourceAmount;
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
	}
	
	protected ExchangesRequest() {}
	
	public Double getSourceAmount() {
		return sourceAmount;
	}
	
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	
	public String getTargetCurrency() {
		return targetCurrency;
	}
	
}

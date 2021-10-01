package com.exchange.api.dto;

public class ExchangesResponse {
	
	private long id;
	private Double targetAmount;
	
	public ExchangesResponse(long id, Double targetAmount) {
		this.id = id;
		this.targetAmount = targetAmount;
	}
	
	protected ExchangesResponse() {}
	
	public long getId() {
		return id;
	}
	
	public Double getTargetAmount() {
		return targetAmount;
	}

}

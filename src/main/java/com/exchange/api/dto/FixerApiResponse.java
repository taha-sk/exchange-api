package com.exchange.api.dto;

import java.util.Map;

public class FixerApiResponse {
	
	private boolean success;
	private Map<String, Double> rates;
	private Map<String, String> error;
	
	protected FixerApiResponse() {}
	
	public boolean isSuccess() {
		return success;
	}
	
	public Map<String, Double> getRates() {
		return rates;
	}
	
	public Map<String, String> getError() {
		return error;
	}

}

package com.exchange.api.dto;

import java.util.List;

import com.exchange.api.model.Exchange;

public class ExchangeList {
	
	private List<Exchange> exchanges;
	
	protected ExchangeList() {}
	
	public ExchangeList(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}
	
	public List<Exchange> getExchanges() {
		return exchanges;
	}

}

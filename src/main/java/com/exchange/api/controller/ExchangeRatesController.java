package com.exchange.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.api.dto.ExchangeRatesResponse;
import com.exchange.api.service.ExchangeRatesService;

@RestController
public class ExchangeRatesController {
	
	@Autowired
	protected ExchangeRatesService exchangeRatesService;
	
	@GetMapping("/exchange-api/exchangeRates")
	public ResponseEntity<ExchangeRatesResponse> getExchangeRates(@RequestParam String currencyPair) {
		return ResponseEntity.ok(exchangeRatesService.getExchangeRate(currencyPair));
	}

}

package com.exchange.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.api.dto.ExchangeList;
import com.exchange.api.dto.ExchangesRequest;
import com.exchange.api.dto.ExchangesResponse;
import com.exchange.api.model.Exchange;
import com.exchange.api.service.ExchangesService;

@RestController
public class ExchangesController {
	
	@Autowired
	protected ExchangesService exchangesService;
	
	@PostMapping("/exchange-api/exchanges")
	public ResponseEntity<ExchangesResponse> getExchangeRates(@RequestBody ExchangesRequest exchangesRequest) {
		return new ResponseEntity<ExchangesResponse>(exchangesService.createTransaction(exchangesRequest.getSourceAmount(), exchangesRequest.getSourceCurrency(), exchangesRequest.getTargetCurrency()), HttpStatus.CREATED);
	}
	
	@GetMapping("/exchange-api/exchanges/{id}")
	public ResponseEntity<Exchange> getExchange(@PathVariable Long id) {
		return ResponseEntity.ok(exchangesService.getExchangeById(id));
	}
	
	@GetMapping("/exchange-api/exchanges")
	public ResponseEntity<ExchangeList> getExchangesWithDate(@RequestParam Date conversionDate) {
		return ResponseEntity.ok(new ExchangeList(exchangesService.getExchangesByConvesionDate(conversionDate)));
	}

}

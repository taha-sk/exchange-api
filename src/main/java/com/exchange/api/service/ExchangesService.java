package com.exchange.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exchange.api.dto.ExchangeRatesResponse;
import com.exchange.api.dto.ExchangesResponse;
import com.exchange.api.exception.ExchangeDoesNotExistException;
import com.exchange.api.exception.InvalidRequestException;
import com.exchange.api.model.Exchange;
import com.exchange.api.repository.ExchangeRepository;

@Service
public class ExchangesService {
	
	@Value("${exhanges.minAmount}")
    protected Double minAmount;
	
	@Autowired
	protected ExchangeRatesService exchangeRatesService;
	
	@Autowired
	protected ExchangeRepository exchangeRepository;
	
	public ExchangesResponse createTransaction(Double sourceAmount, String sourceCurrency, String targetCurrency) {
		
		if(sourceCurrency == null) {
			throw new InvalidRequestException("Source currency is missing.");
		}
		
		if(targetCurrency == null) {
			throw new InvalidRequestException("Target currency is missing.");
		}
		
		if(sourceAmount < minAmount) {
			throw new InvalidRequestException("Cannot exchange currency less than "+ minAmount);
		}
		
		ExchangeRatesResponse rateResponse = exchangeRatesService.getExchangeRate(sourceCurrency+targetCurrency);
		Exchange exchange = Exchange.createTransaction(sourceAmount, sourceCurrency, targetCurrency, rateResponse.getRate());
		
		exchangeRepository.save(exchange);
		return new ExchangesResponse(exchange.getId(), exchange.getTargetAmount());
	}
	
	public Exchange getExchangeById(long exhangeId) {
		return exchangeRepository.findById(exhangeId).orElseThrow(() -> new ExchangeDoesNotExistException(exhangeId));
	}
	
	public List<Exchange> getExchangesByConvesionDate(Date conversionDate) {
		return exchangeRepository.findByConversionDate(conversionDate);
	}

}

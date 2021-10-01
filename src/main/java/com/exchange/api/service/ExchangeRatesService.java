package com.exchange.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exchange.api.dto.ExchangeRatesResponse;
import com.exchange.api.dto.FixerApiResponse;
import com.exchange.api.exception.InvalidRequestException;

@Service
public class ExchangeRatesService {

	@Value("${exhangeRates.url}")
    protected String url;
	
	@Value("${exhangeRates.accessKey}")
	protected String accessKey;
	
	public ExchangeRatesResponse getExchangeRate(String currencyPair) {
		
		if(currencyPair.length() != 6) {
			throw new InvalidRequestException("Currecy pair input size length should match to 6. Current pair is "+currencyPair);
		}
		
		String baseCurrency = currencyPair.substring(0, 3);
		String targetCurrency = currencyPair.substring(3, 6);
		
		if(!"EUR".equals(baseCurrency)) {
			throw new InvalidRequestException("This service only accepts EUR source currency.");
		}
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("access_key", accessKey);
		uriVariables.put("symbols", targetCurrency);
		
		RestTemplate restTemplate = new RestTemplate();
		FixerApiResponse response = restTemplate.getForObject(url, FixerApiResponse.class, uriVariables);
		
		if(response.isSuccess()) {
			return new ExchangeRatesResponse(response.getRates().get(targetCurrency));
		}else{
			throw new InvalidRequestException(response.getError().get("info"));
		}
		
	}

}


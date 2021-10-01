package com.exchange.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exchange.api.controller.ExchangeRatesController;

@SpringBootTest
class ExchangeApiApplicationTests {

	@Autowired
	private ExchangeRatesController exchangeRatesController;
	
	//Sanity Check
	
	@Test
	void contextLoads() {
		assertNotNull(exchangeRatesController);
	}

}

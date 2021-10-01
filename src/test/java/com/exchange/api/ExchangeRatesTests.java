package com.exchange.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeRatesTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getExchangeRatesShouldReturnForCorrectInput() throws Exception {
		this.mockMvc.perform(get("/exchange-api/exchangeRates").param("currencyPair", "EURUSD"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.rate").exists());
	}
	
	@Test
	void getExchangeRatesShouldFailForNoInput() throws Exception {
		this.mockMvc.perform(get("/exchange-api/exchangeRates"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void getExchangeRatesShouldFailForLessInput() throws Exception {
		this.mockMvc.perform(get("/exchange-api/exchangeRates").param("currencyPair", "EURUD"))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("Currecy pair input size length should match to 6. Current pair is EURUD"));
	}
	
	@Test
	void getExchangeRatesShouldFailForNonEurBaseCurrency() throws Exception {
		this.mockMvc.perform(get("/exchange-api/exchangeRates").param("currencyPair", "USDTRY"))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("This service only accepts EUR source currency."));
	}
	
	@Test
	void getExchangeRatesShouldFailForUnknownTargetCurrency() throws Exception {
		this.mockMvc.perform(get("/exchange-api/exchangeRates").param("currencyPair", "EURXXX"))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").exists());
	}

}

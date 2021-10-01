package com.exchange.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.exchange.api.dto.ExchangesRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangesTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void postExchangesShouldReturnForCorrectInput() throws Exception {
		this.mockMvc.perform(post("/exchange-api/exchanges").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new ExchangesRequest(100.00, "EUR", "USD")))
				)
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.targetAmount").exists());
	}
	
	@Test
	void postExchangesShouldFailForMissingSourceCurrency() throws Exception {
		this.mockMvc.perform(post("/exchange-api/exchanges").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new ExchangesRequest(100.00, null, "USD")))
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("Source currency is missing."));
	}
	
	@Test
	void postExchangesShouldFailForMissingTargetCurrency() throws Exception {
		this.mockMvc.perform(post("/exchange-api/exchanges").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new ExchangesRequest(100.00, "EUR", null)))
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("Target currency is missing."));
	}
	
	@Test
	void postExchangesShouldFailForLessAmount() throws Exception {
		this.mockMvc.perform(post("/exchange-api/exchanges").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new ExchangesRequest(90.00, "EUR", "USD")))
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("Cannot exchange currency less than 100.0"));
	}
	
	@Test
	void getExchangesShouldReturnForValidId() throws Exception {
		//Create
		MvcResult result = this.mockMvc.perform(post("/exchange-api/exchanges").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new ExchangesRequest(100.00, "EUR", "USD")))
				)
		.andExpect(status().isCreated()).andReturn();
		
		Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
		
		//Get
		this.mockMvc.perform(get("/exchange-api/exchanges/{id}", id))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(id))
		.andExpect(jsonPath("$.sourceAmount").exists())
		.andExpect(jsonPath("$.sourceCurrency").exists())
		.andExpect(jsonPath("$.targetAmount").exists())
		.andExpect(jsonPath("$.conversionDate").exists());
		
	}
	
	@Test
	void getExchangesShouldFailForInvalidId() throws Exception {
		
		Integer id = -1;
		
		//Get
		this.mockMvc.perform(get("/exchange-api/exchanges/{id}", id))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.message").value("Exchange does not exist with id: " + id));
		
	}
	
	@Test
	void getExchangesShouldReturnForConversionDate() throws Exception {
		//Create
		this.mockMvc.perform(post("/exchange-api/exchanges").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new ExchangesRequest(100.00, "EUR", "USD")))
				)
		.andExpect(status().isCreated());
		
		String conversionDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		//Get
		this.mockMvc.perform(get("/exchange-api/exchanges").param("conversionDate", conversionDate))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.exchanges[0].conversionDate").value(conversionDate));
		
	}

}

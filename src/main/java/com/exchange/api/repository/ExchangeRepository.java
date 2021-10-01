package com.exchange.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exchange.api.model.Exchange;

public interface ExchangeRepository extends CrudRepository<Exchange, Long> {
	
	List<Exchange> findByConversionDate(Date conversionDate);

}

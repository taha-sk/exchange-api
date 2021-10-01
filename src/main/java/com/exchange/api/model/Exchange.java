package com.exchange.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Exchange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Double sourceAmount;
	private String sourceCurrency;
	private String targetCurrency;
	private Double targetAmount;
	
	@Temporal(TemporalType.DATE)
	private Date conversionDate;
	
	protected Exchange() {}
	
	protected Exchange(Double sourceAmount, String sourceCurrency, String targetCurrency, Double targetAmount, Date conversionDate) {
		this.sourceAmount = sourceAmount;
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.targetAmount = targetAmount;
		this.conversionDate = conversionDate;
	}
	
	public static Exchange createTransaction(Double sourceAmount, String sourceCurrency, String targetCurrency, Double rate) {
		Exchange exchange = new Exchange(sourceAmount, sourceCurrency, targetCurrency, sourceAmount * rate, new Date());
		return exchange;
	}

	public long getId() {
		return id;
	}

	public Double getSourceAmount() {
		return sourceAmount;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public Double getTargetAmount() {
		return targetAmount;
	}

	public Date getConversionDate() {
		return conversionDate;
	}

}

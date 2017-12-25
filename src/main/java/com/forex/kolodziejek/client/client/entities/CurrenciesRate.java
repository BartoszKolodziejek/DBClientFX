package com.forex.kolodziejek.client.client.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CurrenciesRate")
public class CurrenciesRate {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	@NotNull  
	private BigDecimal rate;
	
	@NotNull
	private Date date;
	
	@NotNull
	private BigDecimal spread;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "currencyRate", nullable = false)
	private Brokers broker;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "currenciesRatesBase", nullable = false)
	private Currencies currencyBase;
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "currenciesTarget", nullable = false)
	private Currencies currencyTarget;
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getSpread() {
		return spread;
	}
	public void setSpread(BigDecimal spread) {
		this.spread = spread;
	}
	public Brokers getBroker() {
		return broker;
	}
	public void setBroker(Brokers broker) {
		this.broker = broker;
	}
	public Currencies getCurrencyBase() {
		return currencyBase;
	}
	public void setCurrencyBase(Currencies currencyBase) {
		this.currencyBase = currencyBase;
	}
	public Currencies getCurrencyTarget() {
		return currencyTarget;
	}
	public void setCurrencyTarget(Currencies currencyTarget) {
		this.currencyTarget = currencyTarget;
	}
	public CurrenciesRate(BigDecimal rate, Date date, BigDecimal spread, Brokers broker, Currencies currencyBase,
			Currencies currencyTarget) {
		super();
		this.rate = rate;
		this.date = date;
		this.spread = spread;
		this.broker = broker;
		this.currencyBase = currencyBase;
		this.currencyTarget = currencyTarget;
	}
	public CurrenciesRate() {
		super();
	}
	
	
	
	

}

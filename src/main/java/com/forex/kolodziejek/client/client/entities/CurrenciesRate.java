package com.forex.kolodziejek.client.client.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CurrenciesRate")
public class CurrenciesRate {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;

	@NotNull
	@Column(scale=5, precision=10)
	private BigDecimal rate;
	
	@NotNull
    @Column(unique = true)
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
	
	
	public Map<String, String> asMap(){
		Map<String, String> map = new HashMap<>();
		map.put("rate", this.rate.toString());
		map.put("date", date.toString());
		map.put("spread", spread.toString());
		map.put("broker", broker.getBroker_name());
		map.put("base", currencyBase.getCurrency_name());
		map.put("target", currencyBase.getCurrency_name());
		return map;

	}
	

}

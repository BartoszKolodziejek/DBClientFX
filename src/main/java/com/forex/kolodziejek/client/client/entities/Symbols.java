package com.forex.kolodziejek.client.client.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Symbols")
public class Symbols {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	@NotNull  
	@Column(name="symbol_name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "symbolID")
	private Set<Candles> candles;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "symbol")
	private Set<Results> results;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "symbol")
	private Set<Trades> trades;
	
	
	public Set<Candles> getCandles() {
		return candles;
	}
	public void setCandles(Set<Candles> candles) {
		this.candles = candles;
	}
	public String getSymbol_name() {
		return name;
	}
	public void setSymbol_name(String symbol_name) {
		this.name = symbol_name;
	}
	public Symbols(String symbol_name) {
		super();
		this.name = symbol_name;
	}
	public Symbols() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}

package com.forex.kolodziejek.client.client.entities;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Brokers")
public class Brokers {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	@NotNull  
	private String broker_name;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "broker")
	private Set<CurrenciesRate> currencyRate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "broker")
	private Set<Accounts> accounts;
	public String getBroker_name() {
		return broker_name;
	}
	public void setBroker_name(String broker_name) {
		this.broker_name = broker_name;
	}
	public Brokers(String broker_name) {
		super();
		this.broker_name = broker_name;
	}
	public Brokers() {
		super();
	}
	
	

}

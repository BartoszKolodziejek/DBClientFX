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
@Table(name = "Currencies")
public class Currencies {
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currencyBase")
	private Set<CurrenciesRate> currencyRate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currencyTarget")
	private Set<CurrenciesRate> currenciesRatesTarget;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
	private Set<Accounts> accounts;
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	@NotNull  
	@Column(name="currency_name")
	private String name;
	public Set<Accounts> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Accounts> accounts) {
		this.accounts = accounts;
	}
	public String getCurrency_name() {
		return name;
	}
	public void setCurrency_name(String currency_name) {
		this.name = currency_name;
	}
	public Currencies(String currency_name) {
		super();
		this.name = currency_name;
	}
	public Currencies() {
		super();
	}
	
	
	
}

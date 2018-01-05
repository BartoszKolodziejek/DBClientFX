package com.forex.kolodziejek.client.client.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Accounts")
public class Accounts {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	
	@NotNull
	private String name;
	
	@NotNull  
	private BigDecimal lavarage;
	
	@NotNull  
	private BigDecimal deposit;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "broker", nullable = false)
	private Brokers broker;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "users", nullable = false)
	private User users;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "currency", nullable = false)
	private Currencies currency;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private Set<Trades> trades;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLavarage() {
		return lavarage;
	}

	public void setLavarage(BigDecimal lavarage) {
		this.lavarage = lavarage;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Brokers getBroker() {
		return broker;
	}

	public void setBroker(Brokers broker) {
		this.broker = broker;
	}

	public User getUser() {
		return users;
	}

	public void setUser(User user) {
		this.users = user;
	}

	public Currencies getCurrency() {
		return currency;
	}

	public void setCurrency(Currencies currency) {
		this.currency = currency;
	}

	public Set<Trades> getTrades() {
		return trades;
	}

	public void setTrades(Set<Trades> trades) {
		this.trades = trades;
	}



	public Accounts(String name, BigDecimal lavarage, BigDecimal deposit, Brokers broker, User users,
			Currencies currencies) {
		super();
		this.name = name;
		this.lavarage = lavarage;
		this.deposit = deposit;
		this.broker = broker;
		this.users = users;
		this.currency = currency;
		
	}

	public Accounts() {
		super();

	}
	
	

}

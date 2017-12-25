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
@Table(name = "Trades")
public class Trades {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	@NotNull  
	private BigDecimal effect;
	
	@NotNull
	private Date open;
	
	@NotNull
	private Date close;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "strategy", nullable = false)
	private Strategies strategy;
	
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "symbol", nullable = false)
	private Symbols symbol;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "users", nullable = false)
	private User users;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "interval", nullable = false)
	private Interval interval;
	
	



	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "account", nullable = false)
	private Accounts account;

	public BigDecimal getEffect() {
		return effect;
	}

	public void setEffect(BigDecimal effect) {
		this.effect = effect;
	}

	public Date getOpen() {
		return open;
	}

	public void setOpen(Date open) {
		this.open = open;
	}

	public Date getClose() {
		return close;
	}

	public void setClose(Date close) {
		this.close = close;
	}

	public Strategies getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategies strategy) {
		this.strategy = strategy;
	}

	public Symbols getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbols symbol) {
		this.symbol = symbol;
	}

	public User getUser() {
		return users;
	}

	public void setUser(User user) {
		this.users = user;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}
	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}


	public Trades() {
		super();
		
	}

	public Trades(BigDecimal effect, Date open, Date close, Strategies strategy, Symbols symbol, User user,
			Interval interval, Accounts account) {
		super();
		this.effect = effect;
		this.open = open;
		this.close = close;
		this.strategy = strategy;
		this.symbol = symbol;
		this.users = user;
		this.interval = interval;
		this.account = account;
	}

	
	
	

}

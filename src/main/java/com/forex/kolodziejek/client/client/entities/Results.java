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
@Table(name = "Results")
public class Results {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	
	@NotNull
	private BigDecimal var;
	
	@NotNull
	private BigDecimal expected_payoff;
	
	@NotNull
	private Date date;
	
	@NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "interval", nullable = false)
	private Interval interval;
	
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
	 @JoinColumn(name = "broker", nullable = false)
	private Brokers borker;

	public BigDecimal getVar() {
		return var;
	}

	public void setVar(BigDecimal var) {
		this.var = var;
	}

	public BigDecimal getExpected_payoff() {
		return expected_payoff;
	}

	public void setExpected_payoff(BigDecimal expected_payoff) {
		this.expected_payoff = expected_payoff;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
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

	

	public Brokers getBorker() {
		return borker;
	}

	public void setBorker(Brokers borker) {
		this.borker = borker;
	}

	public Results(BigDecimal var, BigDecimal expected_payoff, Date date, Interval interval, Strategies strategy,
			Symbols symbol, User users, Brokers borker) {
		super();
		this.var = var;
		this.expected_payoff = expected_payoff;
		this.date = date;
		this.interval = interval;
		this.strategy = strategy;
		this.symbol = symbol;
		this.users = users;
		this.borker = borker;
	}

	public Results() {
		super();
	}
	
	
	
	
	
	
	
	
	
	

}

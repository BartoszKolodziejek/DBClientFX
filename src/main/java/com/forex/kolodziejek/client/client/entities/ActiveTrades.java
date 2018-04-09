package com.forex.kolodziejek.client.client.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ActiveTrades")
public class ActiveTrades {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	
	
	@NotNull
	private Date open;
	@NotNull
    @Column(scale=5, precision=10)
	private BigDecimal status;
	
	@NotNull
	private String type;
	
	@NotNull
	private BigDecimal size;

    @Column(scale=5, precision=10)
	private BigDecimal stoploss;
	
	private String stoplosstype;
    @Column(scale=5, precision=10)
	private BigDecimal open_price;
	
	
	
	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public String getStoplosstype() {
		return stoplosstype;
	}





	public void setStoplosstype(String stoplosstype) {
		this.stoplosstype = stoplosstype;
	}





	public BigDecimal getOpen_price() {
		return open_price;
	}





	public void setOpen_price(BigDecimal open_price) {
		this.open_price = open_price;
	}





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





	public Date getOpen() {
		return open;
	}





	public void setOpen(Date open) {
		this.open = open;
	}





	public BigDecimal getStatus() {
		return status;
	}





	public void setStatus(BigDecimal status) {
		this.status = status;
	}





	public BigDecimal getStoploss() {
		return stoploss;
	}





	public void setStoploss(BigDecimal stoploss) {
		this.stoploss = stoploss;
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





	public User getUsers() {
		return users;
	}





	public void setUsers(User users) {
		this.users = users;
	}





	public Interval getInterval() {
		return interval;
	}





	public void setInterval(Interval interval) {
		this.interval = interval;
	}





	public Accounts getAccount() {
		return account;
	}





	public void setAccount(Accounts account) {
		this.account = account;
	}



















	





	public ActiveTrades(Date open, BigDecimal status, String type, BigDecimal stoploss, String stoplosstype,
			BigDecimal open_price, Strategies strategy, Symbols symbol, User users, Interval interval,
			Accounts account, BigDecimal size) {
		super();
		this.open = open;
		this.status = status;
		this.type = type;
		this.stoploss = stoploss;
		this.stoplosstype = stoplosstype;
		this.open_price = open_price;
		this.strategy = strategy;
		this.symbol = symbol;
		this.users = users;
		this.interval = interval;
		this.account = account;
		this.size = size;
	}

	public BigDecimal getSize() {
		return size;
	}

	public ActiveTrades() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}

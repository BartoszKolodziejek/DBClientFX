package com.forex.kolodziejek.client.client.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Candles")
public class Candles {
	
	

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 
	 @NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "interval", nullable = false)
		 private Interval interval;
	 
	 @NotNull
	 private Date date;
	 
	 
	 @NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symbol", nullable = false)
	 private Symbols symbolID;
	 
	 @NotNull
	 @Column(scale=5, precision=10)
	 private BigDecimal high;
	 
	 @NotNull
	 @Column(scale=5, precision=10)
	 private BigDecimal low;
	 @NotNull
	 @Column(scale=5, precision=10)
	 private BigDecimal open;
	 @NotNull
	 @Column(scale=5, precision=10)
	 private BigDecimal close;
	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date close_date) {
		this.date = close_date;
	}
	
	
    public Symbols getSymbolID() {
		return symbolID;
	}
	public void setSymbolID(Symbols symbolID) {
		this.symbolID = symbolID;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	public Candles(Interval interval, Date close_date, Symbols symbolID, BigDecimal high, BigDecimal low, BigDecimal open,
			BigDecimal close) {
		super();
		this.interval = interval;
		this.date = close_date;
		this.symbolID = symbolID;
		this.high = high;
		this.low = low;
		this.open = open;
		this.close = close;
	}
	public Candles() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
	 
	@Override
	public String toString() {
		String info =  "{ \"id\":\""+Long.toString(id)+ "\",\"interval\":\"" + interval.getInterval() + "\",\"date\":\"" + date.toString() + "\",\"symol\":\""+  symbolID.getSymbol_name() + "\",\"high\":\"" + high.toString() +  "\",\"low\":\""+ low.toString() +  "\",\"open\":\""+open.toString() + "\",\"close\":\"" + close.toString() +"\"}";
		return info;
	}
	 
	 
	 
	 

}

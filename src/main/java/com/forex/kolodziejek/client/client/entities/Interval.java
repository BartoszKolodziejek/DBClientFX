package com.forex.kolodziejek.client.client.entities;

import java.util.HashSet;
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
@Table(name = "Interval")
public class Interval {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interval")
	private Set<Candles> candles;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interval")
	private Set<Results> results;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interval")
	private Set<Trades> trades;
	
	@NotNull
	private String interval;

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Interval(String interval) {
		super();
		this.interval = interval;
	}

	public Interval() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}

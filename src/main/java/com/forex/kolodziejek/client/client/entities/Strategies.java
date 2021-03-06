package com.forex.kolodziejek.client.client.entities;

import java.util.HashMap;
import java.util.Map;
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
@Table(name = "Strategies")
public class Strategies {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 
	 @NotNull
	 private String strategyName;


	 private String location;

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {

		return location;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "strategy")
		private Set<Results> results;
	 
	 @NotNull
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "users", nullable = false)
	private User users;
	 
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "strategy")
		private Set<Trades> trades;
	 
	/* @OneToMany(fetch = FetchType.LAZY, mappedBy = "strategy")
		private Set<Accounts> accounts;*/

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public User getUser() {
		return users;
	}

	public void setUser(User user) {
		this.users = user;
	}

	public Strategies(String strategyName, User user, String location) {
		super();
		this.strategyName = strategyName;
		this.users = user;
		this.location = location;
	}

	public Strategies() {
		super();
		
	}

	public Map<String, String> asJson(){
		Map<String,String> json = new HashMap<>();
		json.put("name", strategyName);
		json.put("location", location);
		return json;
	}

    public long getId() {
        return id;
    }
}

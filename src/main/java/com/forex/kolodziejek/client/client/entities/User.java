package com.forex.kolodziejek.client.client.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Users")
public class User {
	
	private boolean enabled = true;
	
	
	 @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 @ManyToMany
	  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Roles> roles = new HashSet<>();
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	 private Set<Strategies> strategies;
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
		private Set<Accounts> accounts;
	 
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
		private Set<Trades> trades;
	 
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
		private Set<Results> results;
	 @NotNull
	 private String username;
	 @NotNull
	 private String pass;
	 @NotNull
	 private String e_mail;
	public Set<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public Set<Strategies> getStrategies() {
		return strategies;
	}
	public void setStrategies(Set<Strategies> strategies) {
		this.strategies = strategies;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User(String username, String pass, String e_mail) {
		super();
		this.username = username;
		this.pass = pass;
		this.e_mail = e_mail;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Set<Accounts> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Accounts> accounts) {
		this.accounts = accounts;
	}
	public Set<Trades> getTrades() {
		return trades;
	}
	public void setTrades(Set<Trades> trades) {
		this.trades = trades;
	}
	public Set<Results> getResults() {
		return results;
	}
	public void setResults(Set<Results> results) {
		this.results = results;
	}
	public User(String username, String pass) {
		super();
		this.username = username;
		this.pass = pass;
	}
	public User() {
		
	}
	 
	 
	 

}

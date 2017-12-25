package com.forex.kolodziejek.client.client.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "Roles")
public class Roles {
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();
	
	
	 @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	 @NotNull
	 private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Roles(String name) {
		super();
		this.name = name;
	}
	public Roles() {
		super();
		
	}
	
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	 
	 
	 

}

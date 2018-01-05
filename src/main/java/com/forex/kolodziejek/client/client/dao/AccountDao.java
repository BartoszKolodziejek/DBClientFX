package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Accounts;

public interface AccountDao extends CrudRepository<Accounts, Long> {
	
	public Accounts findByName(String name);

}

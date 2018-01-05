package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Currencies;

public interface CurrencyDao extends CrudRepository<Currencies, Long> {
	
	public Currencies findByName(String name);

}

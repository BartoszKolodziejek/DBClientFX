package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.CurrenciesRate;

public interface CurrencyRateDao extends CrudRepository<CurrenciesRate, Long> {
	
	

}

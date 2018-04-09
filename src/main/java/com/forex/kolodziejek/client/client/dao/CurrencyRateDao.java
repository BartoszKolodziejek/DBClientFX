package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.CurrenciesRate;

import com.forex.kolodziejek.client.client.entities.Currencies;
import java.util.Date;

public interface CurrencyRateDao extends CrudRepository<CurrenciesRate, Long> {

     CurrenciesRate findByCurrencyBaseAndCurrencyTargetAndDate (Currencies base, Currencies target, Date date);
	
	

}

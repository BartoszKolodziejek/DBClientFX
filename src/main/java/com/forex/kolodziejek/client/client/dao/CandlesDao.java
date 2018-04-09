package com.forex.kolodziejek.client.client.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Candles;
import com.forex.kolodziejek.client.client.entities.Interval;
import com.forex.kolodziejek.client.client.entities.Symbols;

public interface CandlesDao extends CrudRepository<Candles, Long> {
	
	public List<Candles> findAllByDateBetweenAndSymbolIDAndIntervalOrderByDateAsc(Date start, Date end, Symbols symbol, Interval interval);

}

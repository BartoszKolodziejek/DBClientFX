package com.forex.kolodziejek.client.client.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.ActiveTrades;

public interface ActiveTradesDao extends CrudRepository<ActiveTrades, Long> {
	
	public void deleteByOpenDate(Date date);

}

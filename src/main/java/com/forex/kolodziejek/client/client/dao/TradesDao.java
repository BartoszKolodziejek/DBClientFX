package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Trades;

public interface TradesDao extends CrudRepository<Trades, Long> {

}

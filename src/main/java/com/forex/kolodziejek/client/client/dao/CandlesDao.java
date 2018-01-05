package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Candles;

public interface CandlesDao extends CrudRepository<Candles, Long> {

}

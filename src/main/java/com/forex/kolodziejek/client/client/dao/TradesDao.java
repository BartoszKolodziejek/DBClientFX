package com.forex.kolodziejek.client.client.dao;

import com.forex.kolodziejek.client.client.entities.Strategies;
import com.forex.kolodziejek.client.client.entities.Symbols;
import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Trades;

import java.util.List;

public interface TradesDao extends CrudRepository<Trades, Long> {

    List<Trades> findAllByStrategyAndSymbol( Strategies strategy, Symbols symbol);

}

package com.forex.kolodziejek.client.client.dao;

import java.util.Date;
import java.util.List;

import com.forex.kolodziejek.client.client.entities.Strategies;
import com.forex.kolodziejek.client.client.entities.Symbols;
import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.ActiveTrades;

public interface ActiveTradesDao extends CrudRepository<ActiveTrades, Long> {

    ActiveTrades getByOpen(Date date);
    ActiveTrades getByOpenAndStrategy(Date date, Strategies strategy);
    List<ActiveTrades> findAllByStrategy(Strategies strategy);


}

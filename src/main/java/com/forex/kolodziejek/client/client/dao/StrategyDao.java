package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Strategies;

public interface StrategyDao extends CrudRepository<Strategies, Long> {

	public Strategies findByStrategyName(String name);
}

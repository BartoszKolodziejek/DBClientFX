package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Brokers;

public interface BrokerDao extends CrudRepository<Brokers, Long> {
	
	public Brokers findByName(String name);
}

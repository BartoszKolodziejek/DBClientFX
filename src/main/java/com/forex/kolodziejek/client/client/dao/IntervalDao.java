package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Interval;

public interface IntervalDao extends CrudRepository<Interval, Long> {
	
public Interval findByInterval(String inteval);	

}

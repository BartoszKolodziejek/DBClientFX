package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Roles;

public interface RolesDao extends CrudRepository<Roles, Long> {
	
	public Roles findByName(String name);

}

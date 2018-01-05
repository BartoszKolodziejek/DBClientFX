package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.User;

public interface UserDao extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);

}

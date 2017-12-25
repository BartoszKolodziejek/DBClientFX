package com.forex.kolodziejek.client.client.dao;

import org.springframework.data.repository.CrudRepository;

import com.forex.kolodziejek.client.client.entities.Symbols;

public interface SymbolDao extends CrudRepository<Symbols, Long> {

}

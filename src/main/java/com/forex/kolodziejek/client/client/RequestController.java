package com.forex.kolodziejek.client.client;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.forex.kolodziejek.client.client.dao.SymbolDao;

@RestController
@Transactional 
public class RequestController {
	
	@Autowired
	private SymbolDao symboldao; 
	
	

}

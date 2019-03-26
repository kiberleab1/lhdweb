package com.lemonde.web.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lemonde.web.domains.Clients;

public interface ClientService {
	Page<Clients> findByPage(Pageable pagable,String type);
	Page<Clients> findByPage(Pageable pagable,String type,String counntry);
	
	Iterable<Clients> findAll();
	void save(Clients clients);
	void delete(int id);
}

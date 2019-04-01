package com.lemonde.web.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lemonde.web.domains.Clients;

public interface ClientService {
	Page<Clients> findByPage(Pageable pagable,String type);
	Page<Clients> findPageByTypeAndCountry(Pageable pagable,String type,String counntry);
	Clients findById(Long id);
	Iterable<Clients> findAll();
	Iterable<Clients> findImages();
	void save(Clients clients);
	void delete(int id);
}

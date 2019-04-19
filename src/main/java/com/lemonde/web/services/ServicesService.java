package com.lemonde.web.services;

import com.lemonde.web.domains.Services;

public interface ServicesService {
	void save(Services service);
	Iterable<Services> findAll();
	void deleteById(int Id);
	Services findById(int Id);
}

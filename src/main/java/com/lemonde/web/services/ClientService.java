package com.lemonde.web.services;

import com.lemonde.web.domains.Clients;

public interface ClientService {
	Iterable<Clients> findAll();
}

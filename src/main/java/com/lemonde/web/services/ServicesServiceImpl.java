package com.lemonde.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.Services;
import com.lemonde.web.repositories.ServiceRepository;

@Service
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	private ServiceRepository serviceRepository;
	@Override
	public Iterable<Services> findAll() {
		return serviceRepository.findAll();
	}

}

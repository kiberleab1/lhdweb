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
	@Override
	public void deleteById(int Id) {
		this.serviceRepository.deleteById((long)Id);
		
	}
	@Override
	public Services findById(int Id) {
		return this.serviceRepository.findById((long)Id).get();
	}
	@Override
	public void save(Services service) {
		this.serviceRepository.save(service);
		
	}

}

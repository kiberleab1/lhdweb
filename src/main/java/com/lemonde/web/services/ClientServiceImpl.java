package com.lemonde.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.Clients;
import com.lemonde.web.repositories.ClientRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Iterable<Clients> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public void save(Clients clients) {
		this.clientRepository.save(clients);

	}

	@Override
	public void delete(int id) {
		this.clientRepository.deleteById((long) id);

	}

	@Override
	public Page<Clients> findByPage(Pageable pagable, String type) {
		return	this.clientRepository.findByType(pagable, type);
		
	}

	@Override
	public Page<Clients> findPageByTypeAndCountry(Pageable pagable, String type, String country) {
		if(country.equalsIgnoreCase("Ethiopia")) {
			return this.clientRepository.findByTypeAndCountry(pagable, type, country);
		}
		return this.clientRepository.findByTypeAndCountryNot(pagable, type, "Ethiopia");
	}

	@Override
	public Clients findById(Long id) {
		return this.clientRepository.findById(id).get();
	}

	@Override
	public Iterable<Clients> findImages() {
		Iterable<Clients> t=this.clientRepository.findByImgPathIsNotNull();
		
		return t;
	}

}

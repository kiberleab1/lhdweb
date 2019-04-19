package com.lemonde.web.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.lemonde.web.domains.Vaccancy;
import com.lemonde.web.repositories.VaccancyReposiotory;

public class VaccancyServiceImpl implements VaccancyService {
	@Autowired
	private VaccancyReposiotory vaccancyReposiotory;
	@Override
	public void save(Vaccancy vaccancy) {
		// TODO Auto-generated method stub
		this.vaccancyReposiotory.save(vaccancy);
		
	}

	@Override
	public Vaccancy findById(long Id) {
		// TODO Auto-generated method stub
		return this.vaccancyReposiotory.findById(Id).get();
	}

}

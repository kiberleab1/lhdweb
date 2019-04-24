package com.lemonde.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.Vaccancy;
import com.lemonde.web.repositories.VaccancyReposiotory;

@Service
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

	@Override
	public List<Vaccancy> findAll() {
		
		return this.vaccancyReposiotory.findAllByIsOn(0);
	}

	@Override
	public void delete(long Id) {
		// TODO Auto-generated method stub
		this.vaccancyReposiotory.deleteById(Id);
		
	}

	@Override
	public int count() {
		return (int) this.vaccancyReposiotory.count();
	}

}

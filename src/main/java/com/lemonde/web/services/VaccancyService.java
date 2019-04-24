package com.lemonde.web.services;

import java.util.List;

import com.lemonde.web.domains.Vaccancy;

public interface VaccancyService {
	public void save(Vaccancy vaccancy);
	public void delete(long Id);
	
	public Vaccancy findById(long Id);
	public List<Vaccancy> findAll();
	public int count();
}

package com.lemonde.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.Vaccancy;

public interface VaccancyReposiotory extends CrudRepository<Vaccancy,Long>{
	public List<Vaccancy> findAllByIsOn(int Id);
}

package com.lemonde.web.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lemonde.web.domains.Research;

public interface ResearchService {
	
	public Iterable<Research> findAll();
	public Research findById(long id);
	public void deleteById(long id);
	public void insert(Research research);
	public Page<Research> findByType(Pageable pagable,String type);
	public int count();
}

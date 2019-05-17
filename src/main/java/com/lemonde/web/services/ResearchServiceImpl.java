package com.lemonde.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.Expirence;
import com.lemonde.web.domains.Research;
import com.lemonde.web.repositories.ResearchRepository;

@Service
public class ResearchServiceImpl implements ResearchService {

	@Autowired
	private ResearchRepository researchRepository;
	@Override
	public Iterable<Research> findAll() {
		// TODO Auto-generated method stub
		return this.researchRepository.findAll();
	}

	@Override
	public Research findById(long id) {
		// TODO Auto-generated method stub
		return this.researchRepository.findById(id).get();
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		this.researchRepository.deleteById(id);
	}

	@Override
	public void insert(Research research) {
		// TODO Auto-generated method stub
		this.researchRepository.save(research);
	}

	@Override
	public Page<Research> findByType(Pageable pagable,String type) {
		return this.researchRepository.findByType(pagable,type);
		// TODO Auto-generated method stub

	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return (int)this.researchRepository.count();
	}

}

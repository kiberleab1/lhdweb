package com.lemonde.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.Expirence;
import com.lemonde.web.repositories.ExpirenceRepository;

@Service
public class ExpirenceServiceImpl implements ExperianceService {

	@Autowired
	private ExpirenceRepository expirenceRepository;

	@Override
	public Page<Expirence> findByPage(Pageable pagable, String type) {
		return expirenceRepository.findByType(pagable, type);
	}

	@Override
	public int countByType(String type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		this.expirenceRepository.deleteById(id);
		
	}

	@Override
	public void save(Expirence experiance) {
		// TODO Auto-generated method stub
		this.expirenceRepository.save(experiance);
	}

	@Override
	public Expirence findById(long id) {
		return this.expirenceRepository.findById(id).get();
	}

}

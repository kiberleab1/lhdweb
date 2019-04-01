package com.lemonde.web.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lemonde.web.domains.Expirence;

public interface ExperianceService {
	Page<Expirence> findByPage(Pageable pagable,String type);
	int countByType(String type);
	void delete(Long id);
	void save(Expirence experiance);
	Expirence findById(long id);
}

package com.lemonde.web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.Expirence;

public interface ExpirenceRepository extends CrudRepository<Expirence,Long> {
	Page<Expirence> findAll(Pageable pageRequest);
	Page<Expirence> findByType(Pageable pageRequest,String type);
	int countByType(String type);
}

package com.lemonde.web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.Research;

public interface ResearchRepository extends CrudRepository<Research, Long> {
	Page<Research> findByType(Pageable pageRequest, String type);
}

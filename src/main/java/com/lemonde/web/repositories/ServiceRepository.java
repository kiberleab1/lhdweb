package com.lemonde.web.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.Services;

public interface ServiceRepository extends CrudRepository<Services,Long> {

}

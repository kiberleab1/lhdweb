package com.lemonde.web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.Clients;


public interface ClientRepository extends CrudRepository<Clients,Long>{
	Page<Clients> findByType(Pageable pageable,String type);
	Page<Clients> findByTypeAndCountry(Pageable pageable,String type,String country);
	Page<Clients> findByTypeAndCountryNot(Pageable pageable,String type,String country);
	
	Iterable<Clients> findByImgPathIsNotNull();
}

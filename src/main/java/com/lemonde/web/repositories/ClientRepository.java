package com.lemonde.web.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.Clients;


public interface ClientRepository extends CrudRepository<Clients,Long>{

}
